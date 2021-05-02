package experis.ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{
    private final Socket client;
    private final BufferedReader input;
    private final PrintWriter output;
    private final List<ClientHandler> clients;

    public ClientHandler(Socket client, List<ClientHandler> clients) throws IOException {
        this.client = client;
        var inputStream = new InputStreamReader(client.getInputStream());
        input = new BufferedReader(inputStream);
        output = new PrintWriter(client.getOutputStream(), true);
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = input.readLine();
                printToAll(msg);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        output.close();

    }

    private void printToAll(String msg) {
        for(ClientHandler clientHandler: clients){
            clientHandler.output.println(msg);
        }
    }
}
