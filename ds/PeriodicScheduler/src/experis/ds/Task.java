package experis.ds;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private final Runnable operation;
    private long period;
    private Boolean alive = true;
    private State state = State.RUNNING;
    private TimeUnit timeUnit;
    private final Object lock = new Object();

    public Task(Runnable operation, long period, TimeUnit timeUnit) {
        this.operation = operation;
        this.period = period;
        this.timeUnit = timeUnit;
    }

    @Override
    public void run() {
        final int nanosToMilli = 1_000_000;
        while (alive) {
            synchronized (lock) {
                while(state == State.SUSPENDED){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                long start = System.nanoTime();
                operation.run();
                long elapsedTime = System.nanoTime() - start;
                long timeToWaitMils = getTimeToWait(elapsedTime) / nanosToMilli;
                int timeToWaitNanos = (int)getTimeToWait(elapsedTime) % nanosToMilli;

                try {
                    lock.wait(timeToWaitMils, timeToWaitNanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public long getTimeToWait(long elapsedTime) {
        long nanoSecondsPeriod = timeUnit.toNanos(period);
        return (nanoSecondsPeriod - elapsedTime) % nanoSecondsPeriod;
    }

    public void stop() {
        synchronized (lock) {
            alive = false;
        }
    }

    public void resume(){
        synchronized (lock) {
            lock.notify();
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setPeriod(long period) {
        synchronized (lock) {
            this.period = period;
        }
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        synchronized (lock) {
            this.timeUnit = timeUnit;
        }
    }
}
