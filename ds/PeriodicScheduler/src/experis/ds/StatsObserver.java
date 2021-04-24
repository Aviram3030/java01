package experis.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class StatsObserver {
    private String status = "Running";
    private long periods;
    private long totalTimeException;
    private long totalTimeExecution;
    private long averageTimeExecution;
    private long failures;
    private final List<Exception> exceptions = new ArrayList<>();

    public void onPeriodCompleted(long delta) {
        periods++;
        totalTimeExecution += delta;
        averageTimeExecution = totalTimeExecution / periods;
    }

    public void onStatus(String status){
        this.status = status;
    }

    public void onException(long delta, Exception x) {
        totalTimeException += delta;
        failures++;
        exceptions.add(x);
    }

    public String toString() {
        return "StatsObserver{" +
                "\n status = " + status +
                "\n periods = " + periods +
                "\n totalTimeExecution = " + totalTimeExecution +
                "\n averageTimeExecution = " + averageTimeExecution +
                "\n totalTimeException = " + totalTimeException +
                "\n failures = " + failures +
                "\n exceptions = " + exceptions +
                '}';
    }
}
