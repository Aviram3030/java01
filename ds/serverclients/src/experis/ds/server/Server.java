package experis.ds.server;

import experis.ds.executors.CommandExecutor;
import experis.ds.executors.ICommandExecutor;
import experis.ds.client.Room;
import experis.ds.client.ClientHandler;
import experis.ds.particpants.ParticipantUser;
import experis.ds.client.ParticipantUserCreator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private final ServerSocket serverSocket;
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final ICommandExecutor commandExecutor;
    private final Room lobby = new Room("Lobby");
    private final ParticipantUserCreator participantUserCreator;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        ConcurrentHashMap<String, ParticipantUser> users = new ConcurrentHashMap<>();
        commandExecutor = makeCommandExecutor(users);
        participantUserCreator = new ParticipantUserCreator(users);
    }

    private ICommandExecutor makeCommandExecutor(ConcurrentHashMap<String, ParticipantUser> users) {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(lobby);

        return new CommandExecutor(rooms, users);
    }

    public void execute() throws IOException {
        while (true) {
            System.out.println("Waiting for clients");
            Socket client = serverSocket.accept();
            System.out.println("Server connected to client");
            ParticipantUser clientUser = participantUserCreator.create(client, lobby);
            ClientHandler clientHandler = new ClientHandler(client, commandExecutor, clientUser);

            pool.execute(clientHandler);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(7777);
        server.execute();
    }
}
