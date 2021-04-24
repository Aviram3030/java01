package experis.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

interface IStatsObserver {

    void onStart(long activeFrom);

    void onPeriodCompleted(long delta);

    void onException(long delta, Exception x);

    void onEnd(long overAllTime);
}


class ActiveObserver implements IStatsObserver {
    private Runner runner;
    private short failures;

    public ActiveObserver(Runner runner) {
        this.runner = runner;
    }

    @Override
    public void onStart(long activeFrom) {

    }

    @Override
    public void onPeriodCompleted(long delta) {

    }

    @Override
    public void onException(long delta, Exception x) {
        this.failures++;
        if(failures == 3){
            runner.stop();
        }
    }

    @Override
    public void onEnd(long overAllTime) {

    }
}


class StatsObservers implements IStatsObserver{

    private AtomicLong start = new AtomicLong();
    private long periods;
    private long totalTimeExecution;
    private AtomicLong failures = new AtomicLong();;
    private List exceptions = new ArrayList(16);
    private long finishAfter;

    @Override
    public void onStart(long activeFrom) {
        this.start.addAndGet(activeFrom);
    }

    @Override
    public void onPeriodCompleted(long delta) {
        this.periods++;
        this.totalTimeExecution += delta;
    }

    @Override
    public void onException(long delta, Exception x) {
        this.failures.incrementAndGet();
        this.exceptions.add(x);
    }

    @Override
    public void onEnd(long overAllTime) {
        this.finishAfter = overAllTime;
    }

    @Override
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

class Runner {
    private final IStatsObserver observer;
    private Thread thread;
    private AtomicBoolean active = new AtomicBoolean();
    public Runner(IStatsObserver observer, Runnable userRunnable){
        this.observer = observer;
        active.set(true);

        var activeFrom = System.nanoTime();
        observer.onStart(activeFrom);
        thread = new Thread(() -> {
            while(active.get()) {
                var start = System.nanoTime();
                try {
                    userRunnable.run();
                    var delta = System.nanoTime() - start;
                    observer.onPeriodCompleted(delta);

                } catch (Exception x) {
                    var delta = System.nanoTime() - start;
                    observer.onException(delta, x);
                }
            }

            observer.onEnd(System.nanoTime() - activeFrom);
        });

        thread.start();
    }

    public void stop() {
        active.set(false);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class Main {

    public static void main(String[] args) {
        var op = new StatsObservers();
//        op = new ActiveObserver(r)

        int[] v = {1,2,3,4};
        var r = new Runner(op, () ->{
            delay(250);
            System.out.println(v[5]);
        });

        if(false){
            delay(2_000);
            System.out.println(op);

            delay(2_000);
            System.out.println(op);

            delay(3_000);
        }else{
            delay(3_000);
        }

        r.stop();

        System.out.println(op.toString());

    }

    private static void delay(long d) {
        System.out.println("working....");
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}