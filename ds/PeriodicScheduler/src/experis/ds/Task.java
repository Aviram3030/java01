package experis.ds;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private final Runnable operation;
    private long period;
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
        while (!isFinished()) {
            synchronized (lock) {
                while(isSuspended()){
                    try {
                        lock.wait();
                        if(isFinished()){
                            return;
                        }
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

    private long getTimeToWait(long elapsedTime) {
        long nanoSecondsPeriod = timeUnit.toNanos(period);
        return (nanoSecondsPeriod - elapsedTime) % nanoSecondsPeriod;
    }

    public void resume(){
        synchronized (lock) {
            lock.notify();
        }
    }

    public void setState(State state) {
        synchronized (lock) {
            this.state = state;
        }
    }

    public Boolean isRunning(){
        return state == State.RUNNING;
    }

    public Boolean isSuspended(){
        return state == State.SUSPENDED;
    }

    public Boolean isFinished(){
        return state == State.FINISHED;
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
