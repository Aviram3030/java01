package experis.ds.time;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimeOut implements Runnable{
    private final TimeUnit timeUnit;
    private final long time;
    private boolean running = false;
    private final Socket client;
    private final Lock timerLock = new ReentrantLock();
    private final Condition condition = timerLock.newCondition();

    public TimeOut(TimeUnit timeUnit, long time, Socket client) {
        this.timeUnit = timeUnit;
        this.time = time;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                timerLock.lock();
                condition.await(time, timeUnit);
                timerLock.unlock();
                if (!running) {
                    client.close();
                    break;
                }
                running = false;
                timerLock.lock();
                condition.await();
                timerLock.unlock();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void wakeUp(){
        timerLock.lock();
        condition.signal();
        timerLock.unlock();
    }

    public void setRunning(){
        running = true;
        wakeUp();
    }
}

