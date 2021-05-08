package experis.ds.time;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TimeOut implements Runnable{
    private final TimeUnit timeUnit;
    private final long time;
    private boolean alive = true;
    private boolean running = false;
    private final Socket client;
    private Thread currentThread;

    public TimeOut(TimeUnit timeUnit, long time, Socket client) {
        this.timeUnit = timeUnit;
        this.time = time;
        this.client = client;
    }

    @Override
    public void run() {
        currentThread = Thread.currentThread();
        try {
            while (true) {
                synchronized (currentThread) {
                    currentThread.wait(timeUnit.toMillis(time));
                }
                if (!running) {
                    client.close();
                    break;
                }
                running = false;
                synchronized (currentThread) {
                    currentThread.wait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void wakeUp(){
        synchronized (currentThread){
            currentThread.notify();
        }
    }

    public void setRunning(){
        running = true;
        wakeUp();
    }
}
