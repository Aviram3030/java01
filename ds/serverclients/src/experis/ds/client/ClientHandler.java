package experis.ds.client;

import experis.ds.commands.ITransformation;
import experis.ds.executorcaller.ExecutorCaller;
import experis.ds.commands.CommandTypeFactory;
import experis.ds.time.TimeOut;
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
    private final TimeOut timer;

    public ClientHandler(Socket client, ExecutorCaller executorCaller, ParticipantUser participantUser,
                         BufferedReader input, PrintWriter output, ITransformation transformation) {
        this.client = client;
        this.participantUser = participantUser;
        this.input = input;
        this.output = output;
        this.executorCaller = executorCaller;
        this.transformation = transformation;
        timer = new TimeOut(TimeUnit.SECONDS, 5, client);
    }

    @Override
    public void run() {
        Thread timerThread = new Thread(timer);
        timerThread.start();
        try {
            while (true) {
                executeCommands();
                if(!participantUser.isAlive()){
                    break;
                }
                timer.wakeUp();
            }
        }catch(IOException ignored){
        }
        finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeCommands() throws IOException {
        String msg = input.readLine();
        timer.setRunning();
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
