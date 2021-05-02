package experis.ds;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private final int PORT = 7777;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final ExecutorService pool = Executors.newFixedThreadPool(4);

    public void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        while(true) {
            System.out.println("Waiting for clients");
            Socket client = serverSocket.accept();
            System.out.println("Server connected to client");
            ClientHandler clientHandler = new ClientHandler(client, clients);
            clients.add(clientHandler);

            pool.execute(clientHandler);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.execute();
    }
}
