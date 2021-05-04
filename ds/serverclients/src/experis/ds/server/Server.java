package experis.ds.server;

import experis.ds.executors.CommandExecutor;
import experis.ds.executors.ICommandExecutor;
import experis.ds.particpants.ParticipantBot;
import experis.ds.rooms.Lobby;
import experis.ds.rooms.Room;
import experis.ds.client.ClientHandler;
import experis.ds.particpants.ParticipantUser;
import experis.ds.particpants.ParticipantUserCreator;

import java.io.IOException;
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
    private final ICommandExecutor commandExecutor;
    private final ParticipantUserCreator participantUserCreator;
    private final ParticipantBot bot = new ParticipantBot(TimeUnit.SECONDS, 10);

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        ConcurrentHashMap<String, ParticipantUser> users = new ConcurrentHashMap<>();
        commandExecutor = makeCommandExecutor(users);
        participantUserCreator = new ParticipantUserCreator(users);
    }

    private ICommandExecutor makeCommandExecutor(ConcurrentHashMap<String, ParticipantUser> users) {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(Lobby.getLobby());
        return new CommandExecutor(rooms, users);
    }

    public void execute() throws IOException {
        pool.execute(bot);
        while (true) {
            System.out.println("Waiting for clients");
            Socket client = serverSocket.accept();
            System.out.println("Server connected to client");
            ParticipantUser participantUserUser = participantUserCreator.create(client);
            ClientHandler clientHandler = new ClientHandler(client, commandExecutor, participantUserUser);

            pool.execute(clientHandler);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(7777);
        server.execute();
    }
}
