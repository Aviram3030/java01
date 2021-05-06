package experis.ds.client;

import experis.ds.commands.ITransformation;
import experis.ds.executorcaller.ExecutorCaller;
import experis.ds.commands.CommandTypeFactory;
import experis.ds.executors.TimeOut;
import experis.ds.particpants.ParticipantUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientHandler implements Runnable{
    private final Socket client;
    private final BufferedReader input;
    private final PrintWriter output;
    private final ParticipantUser participantUser;
    private final ExecutorCaller executorCaller;
    private final CommandTypeFactory commandTypeFactory = new CommandTypeFactory();
    private final ITransformation transformation;
    private final TimeOut timer = new TimeOut(TimeUnit.SECONDS, 30);

    public ClientHandler(Socket client, ExecutorCaller executorCaller, ParticipantUser participantUser,
                         BufferedReader input, PrintWriter output, ITransformation transformation) {
        this.client = client;
        this.participantUser = participantUser;
        this.input = input;
        this.output = output;
        this.executorCaller = executorCaller;
        this.transformation = transformation;
    }

    @Override
    public void run() {
        try {
            while (true) {
                long start = System.nanoTime();
                String msg = input.readLine();
                long elapsedTime = System.nanoTime() - start;
                timer.checkTimeOut(elapsedTime);
                if(!timer.isAlive()){
                    break;
                }

                executeCommands(msg);
                if(!participantUser.isAlive()){
                    break;
                }
            }
            output.println("Good bye");
            close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void executeCommands(String msg){
        var type = commandTypeFactory.getType(msg);
        msg = transformation.transform(msg, type);
        executorCaller.execute(participantUser, msg, type);
    }

    private void close() throws IOException {
        output.close();
        input.close();
        client.close();
    }
}
