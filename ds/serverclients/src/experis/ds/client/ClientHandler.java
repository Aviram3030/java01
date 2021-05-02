package experis.ds.client;

import experis.ds.ICommandExecutor;
import experis.ds.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket client;
    private final BufferedReader input;
    private final PrintWriter output;
    private final ClientUser clientUser;
    private final ICommandExecutor commandExecutor;

    public ClientHandler(Socket client, Room lobby, ICommandExecutor commandExecutor) throws IOException {
        this.client = client;
        var inputStream = new InputStreamReader(client.getInputStream());
        input = new BufferedReader(inputStream);
        output = new PrintWriter(client.getOutputStream(), true);
        clientUser = new ClientUser(client, lobby);
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void run() {
        try {
            while (!client.isClosed()) {
                String msg = input.readLine();
                commandExecutor.execute(clientUser, msg);
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
}
