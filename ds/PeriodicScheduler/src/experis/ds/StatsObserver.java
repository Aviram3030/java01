package experis.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class StatsObserver {
    private AtomicLong start = new AtomicLong();
    private String status = "Running";
    private long periods;
    private long totalTimeExecution;
    private AtomicLong failures = new AtomicLong();;
    private List exceptions = new ArrayList(16);
    private long finishAfter;

    public void onStart(long activeFrom) {
        this.start.addAndGet(activeFrom);
    }

    public void onPeriodCompleted(long delta) {
        this.periods++;
        this.totalTimeExecution += delta;
    }

    public void onStatus(String status){
        this.status = status;
    }

    public void onException(long delta, Exception x) {
        this.failures.incrementAndGet();
        this.exceptions.add(x);
    }

    public void onEnd(long overAllTime) {
        this.finishAfter = overAllTime;
    }

    public String toString() {
        return "StatsObserver{" +
                "\n start=" + start.get() +
                "\n periods=" + periods +
                "\n totalTimeExecution=" + totalTimeExecution +
                "\n failures=" + failures.get() +
                "\n exceptions=" + exceptions +
                "\n finishAfter=" + finishAfter +
                '}';
    }
}
