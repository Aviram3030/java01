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
        while (!finished()) {
            synchronized (lock) {
                while(suspended()){
                    try {
                        lock.wait();
                        if(finished()){
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

    // make class Time ???
    private long getTimeToWait(long elapsedTime) {
        long nanoSecondsPeriod = timeUnit.toNanos(period);
        return (nanoSecondsPeriod - elapsedTime) % nanoSecondsPeriod;
    }

    public void resume(){
        synchronized (lock) {
            state = State.RUNNING;
            lock.notify();
        }
    }

    public void suspend(){
        synchronized (lock) {
            state = State.SUSPENDED;
        }
    }

    public void stop(){
        synchronized (lock) {
            state = State.FINISHED;
        }
    }

    public Boolean isRunning(){
        synchronized (lock) {
            return running();
        }
    }

    private Boolean running(){
        return state == State.RUNNING;
    }

    public Boolean isSuspended(){
        synchronized (lock) {
            return suspended();
        }
    }

    private Boolean suspended(){
        return state == State.SUSPENDED;
    }

    public Boolean isFinished(){
        synchronized (lock) {
            return finished();
        }
    }

    private Boolean finished(){
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
