package experis.ds.client;

import experis.ds.commands.Transformation;
import experis.ds.executors.ICommandExecutor;
import experis.ds.commands.CommandTypeFactory;
import experis.ds.executors.TimeOut;
import experis.ds.particpants.ParticipantUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientHandler implements Runnable{
    private final Socket client;
    private final BufferedReader input;
    private final PrintWriter output;
    private final ParticipantUser participantUser;
    private final ICommandExecutor commandExecutor;
    private final CommandTypeFactory commandTypeFactory = new CommandTypeFactory();
    private final Transformation transformation = new Transformation();
    private final TimeOut timer;

    public ClientHandler(Socket client, ICommandExecutor commandExecutor, ParticipantUser participantUser) throws IOException {
        this.client = client;
        this.participantUser = participantUser;
        var inputStream = new InputStreamReader(client.getInputStream());
        input = new BufferedReader(inputStream);
        output = new PrintWriter(client.getOutputStream(), true);
        this.commandExecutor = commandExecutor;
        timer = new TimeOut(client, TimeUnit.SECONDS, 30);
    }

    @Override
    public void run() {
        try {
            while (true) {
                long start = System.nanoTime();
                String msg = input.readLine();
                long elapsedTime = System.nanoTime() - start;
                timer.start(elapsedTime);
                if(!client.isClosed()){
                    break;
                }
                var type = commandTypeFactory.getType(msg);
                msg = transformation.transform(msg, type);
                commandExecutor.execute(participantUser, msg, type);
            }
            input.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        output.close();

    }
}
