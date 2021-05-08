package experis.ds.server;

import experis.ds.commands.Transformation;
import experis.ds.executorcaller.ExecutorCaller;
import experis.ds.particpants.*;
import experis.ds.rooms.Lobby;
import experis.ds.rooms.Room;
import experis.ds.client.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {
    private final ServerSocket serverSocket;
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final ParticipantUserProducer participantUserProducer;
    private final ParticipantAdminProducer participantAdminProducer;
    private final Bot bot = new Bot(TimeUnit.SECONDS, 30);
    private final ExecutorCaller executorCaller;
    private final ConcurrentHashMap<Socket, ParticipantUser> clients = new ConcurrentHashMap<>();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        ParticipantsNames participants = new ParticipantsNames();
        participantUserProducer = new ParticipantUserProducer(participants);
        participantAdminProducer = new ParticipantAdminProducer(participants);
        executorCaller = makeExecutorCaller(participants);
    }

    public void execute() {
        pool.execute(bot);
        System.out.println("Waiting for clients");
        try {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Server connected to client");
                var output = new PrintWriter(client.getOutputStream(), true);

                var input = createReader(client);
                String name = input.readLine();
                interfaceUserOrAdmin(output);
                var participantUser = getUser(input, output, name);
                if (participantUser == null) {
                    client.close();
                    continue;
                }
                clients.put(client, participantUser);
                var clientHandler = new ClientHandler(client, executorCaller, participantUser, input, output,
                        new Transformation());
                pool.execute(clientHandler);
            }
        } catch (IOException ignored) {
        } finally {
            shutdownAndAwaitTermination();

        }
        System.exit(0);
    }

    private void interfaceUserOrAdmin(PrintWriter output) {
        output.println("Type 1 to log in as admin");
        output.println("Type any to log in as user");
    }

    private ParticipantUser getUser(BufferedReader input, PrintWriter output, String name) throws IOException {
        String s = input.readLine();
        if (s.equals("1")) {
            output.println("Type password");
            String password = input.readLine();
            return participantAdminProducer.create(output, name, password, serverSocket);
        } else {
            return participantUserProducer.create(output, name);
        }
    }

    private BufferedReader createReader(Socket client) throws IOException {
        var inputStream = new InputStreamReader(client.getInputStream());
        return new BufferedReader(inputStream);
    }

    private ExecutorCaller makeExecutorCaller(ParticipantsNames participants) {
        HashSet<Room> rooms = new HashSet<>();
        rooms.add(Lobby.getLobby());
        return new ExecutorCaller(rooms, participants);
    }

    private void shutdownAndAwaitTermination() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                closeAllClients();
                if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
        }
    }

    private void closeAllClients() {
        for (Socket client : clients.keySet()) {
            try {
                clients.get(client).printMessage("Server shutting down");
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(7777);
        server.execute();
    }
}
