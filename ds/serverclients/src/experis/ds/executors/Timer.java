package experis.ds.executors;

import java.util.concurrent.TimeUnit;

public class Timer {
    private final TimeUnit timeUnit;
    private final long time;

    public Timer(TimeUnit timeUnit, long time) {
        this.timeUnit = timeUnit;
        this.time = time;
    }

    public Boolean checkTimeOut(long elapsed){
        if(elapsed > timeUnit.toNanos(time)){
            return true;
        }
        return false;
    }
}
