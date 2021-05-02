package experis.ds.server;

import experis.ds.CommandExecutor;
import experis.ds.ICommandExecutor;
import experis.ds.Room;
import experis.ds.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final Room lobby = new Room("Lobby");
    private final ICommandExecutor commandExecutor;

    public Server(int port ,CommandExecutor commandExecutor) throws IOException {
        this.commandExecutor = commandExecutor;
        serverSocket = new ServerSocket(port);
    }

    public void execute() throws IOException {

        while(true) {
            System.out.println("Waiting for clients");
            Socket client = serverSocket.accept();
            System.out.println("Server connected to client");
            ClientHandler clientHandler = new ClientHandler(client, lobby, commandExecutor);
            clients.add(clientHandler);

            pool.execute(clientHandler);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(7777, new CommandExecutor());
        server.execute();
    }
}
