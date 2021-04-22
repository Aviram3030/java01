package experis.ds;

import experis.ds.lmbda.SleepCalculator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task implements Runnable {
    private final Runnable operation;
    private long period;
    private State state = State.RUNNING;
    private TimeUnit timeUnit;
    private final Lock guard = new ReentrantLock(true);
    private final Condition active = guard.newCondition();
    private final SleepCalculator sleepCalculator;

    public Task(Runnable operation, long period, TimeUnit timeUnit, SleepCalculator sleepCalculator) {
        this.operation = operation;
        this.period = period;
        this.timeUnit = timeUnit;
        this.sleepCalculator = sleepCalculator;
    }

    @Override
    public void run() {
        while (true) {
            guard.lock();
            if(finished()){
                guard.unlock();
                break;
            }

            while (suspended()) {
                try {
                    active.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            execute();
            guard.unlock();
        }
    }

    private void execute(){
        long start = System.nanoTime();
        operation.run();
        long elapsedTime = System.nanoTime() - start;
        sleep(elapsedTime);
    }

    private void sleep(long elapsedTime) {
        final int nanosToMilli = 1_000_000;
        long timeToWait = getTimeToWait(elapsedTime);
        long timeToWaitMils = timeToWait / nanosToMilli;
        int timeToWaitNanos = (int) timeToWait % nanosToMilli;

        goOff(timeToWaitMils, timeToWaitNanos);
    }

    private long getTimeToWait(long elapsedTime) {
        long timePeriod = timeUnit.toNanos(period);
        return sleepCalculator.calculate(timePeriod, elapsedTime);
    }

    private void goOff(long timeToWaitMils, int timeToWaitNanos){
        try {
            Thread.sleep(timeToWaitMils, timeToWaitNanos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        guard.lock();
        state = State.RUNNING;
        active.signal();
        guard.unlock();
    }

    public void suspend() {
        guard.lock();
        state = State.SUSPENDED;
        guard.unlock();
    }

    public void stop() {
        guard.lock();
        state = State.FINISHED;
        guard.unlock();
    }

    public Boolean isRunning() {
        guard.lock();
        Boolean present = running();
        guard.unlock();
        return present;
    }

    private Boolean running() {
        return state == State.RUNNING;
    }

    public Boolean isSuspended() {
        guard.lock();
        Boolean present = suspended();
        guard.unlock();
        return present;
    }

    private Boolean suspended() {
        return state == State.SUSPENDED;
    }

    public Boolean isFinished() {
        guard.lock();
        Boolean present = finished();
        guard.unlock();
        return present;
    }

    private Boolean finished() {
        return state == State.FINISHED;
    }

    public void setPeriod(long period) {
        guard.lock();
        this.period = period;
        guard.unlock();
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        guard.lock();
        this.timeUnit = timeUnit;
        guard.unlock();
    }
}
