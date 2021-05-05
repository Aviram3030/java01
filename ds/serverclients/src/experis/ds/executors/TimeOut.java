package experis.ds.executors;

import java.util.concurrent.TimeUnit;

public class TimeOut {
    private final TimeUnit timeUnit;
    private final long time;
    private boolean alive = true;

    public TimeOut(TimeUnit timeUnit, long time) {
        this.timeUnit = timeUnit;
        this.time = time;
    }

    public void checkTimeOut(long elapsed){
        if(elapsed > timeUnit.toNanos(time)){
            alive = false;
        }
    }

    public boolean isAlive(){
        return alive;
    }
}
