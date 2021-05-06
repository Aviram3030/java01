package experis.ds.server;

import experis.ds.commands.Transformation;
import experis.ds.executorcaller.ExecutorCaller;
import experis.ds.particpants.ParticipantBot;
import experis.ds.rooms.Lobby;
import experis.ds.rooms.Room;
import experis.ds.client.ClientHandler;
import experis.ds.particpants.ParticipantUser;
import experis.ds.particpants.ParticipantUserCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {
    private final ServerSocket serverSocket;
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final ParticipantUserCreator participantUserCreator;
    private final ParticipantBot bot = new ParticipantBot(TimeUnit.SECONDS, 30);
    private final ExecutorCaller executorCaller;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        ConcurrentHashMap<String, ParticipantUser> users = new ConcurrentHashMap<>();
        participantUserCreator = new ParticipantUserCreator(users);
        executorCaller = makeExecutorCaller(users);
    }

    public void execute() {
        pool.execute(bot);
        System.out.println("Waiting for clients");
        try {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Server connected to client");

                var inputStream = new InputStreamReader(client.getInputStream());
                var input = new BufferedReader(inputStream);
                String name = input.readLine();
                var output = new PrintWriter(client.getOutputStream(), true);
                ParticipantUser participantUserUser = participantUserCreator.create(output, name);
                ClientHandler clientHandler = new ClientHandler(client, executorCaller, participantUserUser, input, output,
                                                                new Transformation());
                pool.execute(clientHandler);
            }
        } catch (IOException e) {
            shutdownAndAwaitTermination();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ExecutorCaller makeExecutorCaller(ConcurrentHashMap<String, ParticipantUser> users) {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(Lobby.getLobby());
        return new ExecutorCaller(rooms, users);
    }

    private void shutdownAndAwaitTermination() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(7777);
        server.execute();
    }

}
