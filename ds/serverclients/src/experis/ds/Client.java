package experis.ds;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    //ip = "127.0.0.1";
    //port = 7777;
    private final Socket socket;
    private final ServerHandler serverHandler;
    private final PrintWriter output;
    private final String name;
    private final Scanner reader;


    public Client(String ip, int port, String name, Scanner reader) throws IOException {
        socket = new Socket(ip, port);

        serverHandler = new ServerHandler(socket);
        output = new PrintWriter(socket.getOutputStream(), true);
        this.name = name;
        this.reader = reader;
    }

    public void execute() throws IOException {
        new Thread(serverHandler).start();
        System.out.println("You are in chat now");

        while(true) {
            String command = reader.nextLine();
            if(command.equals("quit")){
                break;
            }
            output.println(name + " says: " +command);
        }

        socket.close();
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter your name");
        String name = reader.nextLine();

        var client = new Client("127.0.0.1", 7777, name, reader);
        client.execute();

    }
}
