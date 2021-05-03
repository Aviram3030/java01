package experis.ds.client;

import experis.ds.executors.ICommandExecutor;
import experis.ds.commands.CommandTypeFactory;
import experis.ds.particpants.ParticipantUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket client;
    private final BufferedReader input;
    private final PrintWriter output;
    private final ParticipantUser clientUser;
    private final ICommandExecutor commandExecutor;
    private final CommandTypeFactory commandTypeFactory = new CommandTypeFactory();

    public ClientHandler(Socket client, ICommandExecutor commandExecutor, ParticipantUser clientUser) throws IOException {
        this.client = client;
        this.clientUser = clientUser;
        var inputStream = new InputStreamReader(client.getInputStream());
        input = new BufferedReader(inputStream);
        output = new PrintWriter(client.getOutputStream(), true);
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void run() {
        try {
            while (!client.isClosed()) {
                String msg = input.readLine();
                var type = commandTypeFactory.getType(msg);
                commandExecutor.execute(clientUser, msg, type);
            }
            input.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        output.close();

    }
}
