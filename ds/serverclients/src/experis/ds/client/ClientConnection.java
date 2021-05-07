package experis.ds.client;

import experis.ds.server.ServerHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection {
    private final Socket socket;
    private final ServerHandler serverHandler;
    private final PrintWriter output;
    private final Scanner reader;

    public ClientConnection(String ip, int port, Scanner reader) throws IOException {
        socket = new Socket(ip, port);
        serverHandler = new ServerHandler(socket);
        output = new PrintWriter(socket.getOutputStream(), true);
        this.reader = reader;
    }

    public void start() {
        System.out.println("Enter your name");
        String name = reader.nextLine();
        output.println(name);

        Thread thread = new Thread(serverHandler);
        thread.start();
        System.out.println("Connected to server");

        while(!socket.isClosed()) {
            String command = reader.nextLine();
            output.println(command);
        }

        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        var client = new ClientConnection("127.0.0.1", 7777, reader);
        client.start();

    }
}
