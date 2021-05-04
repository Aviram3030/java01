package experis.ds.executors;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TimeOut {
    private final TimeUnit timeUnit;
    private final long time;
    private final Socket client;

    public TimeOut(Socket client, TimeUnit timeUnit, long time) {
        this.timeUnit = timeUnit;
        this.time = time;
        this.client = client;
    }

    public void start(long elapsed) throws IOException {
        if(checkTimeOut(elapsed)){
            client.close();
        }
    }
    private Boolean checkTimeOut(long elapsed){
        if(elapsed > timeUnit.toNanos(time)){
            return true;
        }
        return false;
    }
}
