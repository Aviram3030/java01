package experis.ds;

import experis.ds.lmbda.SleepCalculator;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Task extends RecursiveAction {
    private final TimeUnit sleepForNanos = TimeUnit.NANOSECONDS;
    private final Runnable operation;
    private long time;
    private Status status = Status.RUNNING;
    private final Lock guard = new ReentrantLock();
    private final Condition active = guard.newCondition();
    private final Lock timeLock = new ReentrantLock();
    private final SleepCalculator sleepCalculator;
    private final StatsObserver statsObserver = new StatsObserver();

    public Task(Runnable operation, SleepCalculator sleepCalculator,long time) {
        this.operation = operation;
        this.sleepCalculator = sleepCalculator;
        this.time = time;
    }

    @Override
    protected void compute() {
        while (true) {
            guard.lock();
            while (suspended()) {
                try {
                    active.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(finished()){
                guard.unlock();
                break;
            }
            guard.unlock();

            long elapsedTime = execute();
            timeLock.lock();
            long timeToSleep = sleepCalculator.calculate(time, elapsedTime);
            sleep(timeToSleep);
            timeLock.unlock();
        }
    }

    private long execute(){
        long elapsedTime;
        long start = System.nanoTime();
        try {
            operation.run();
        }
        catch(Exception e){
            elapsedTime = System.nanoTime() - start;
            statsObserver.onException(elapsedTime, e);
            return elapsedTime;
        }
        elapsedTime = System.nanoTime() - start;
        statsObserver.onPeriodCompleted(elapsedTime);
        return System.nanoTime() - start;
    }

    private void sleep(long timeToSleep) {
        try {
            sleepForNanos.sleep(timeToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        guard.lock();
        status = Status.RUNNING;
        statsObserver.onStatus("Running");
        active.signal();
        guard.unlock();
    }

    public void suspend() {
        guard.lock();
        status = Status.SUSPENDED;
        statsObserver.onStatus("Suspended");
        guard.unlock();
    }

    public void stop() {
        guard.lock();
        status = Status.FINISHED;
        active.signal();
        statsObserver.onStatus("Finished");
        guard.unlock();
    }

    public Boolean isRunning() {
        guard.lock();
        Boolean present = running();
        guard.unlock();
        return present;
    }

    private Boolean running() {
        return status == Status.RUNNING;
    }

    public Boolean isSuspended() {
        guard.lock();
        Boolean present = suspended();
        guard.unlock();
        return present;
    }

    private Boolean suspended() {
        return status == Status.SUSPENDED;
    }

    public Boolean isFinished() {
        guard.lock();
        Boolean present = finished();
        guard.unlock();
        return present;
    }

    private Boolean finished() {
        return status == Status.FINISHED;
    }

    public void setTime(long time) {
        timeLock.lock();
        this.time = time;
        timeLock.unlock();
    }

    public void printScheduleInfo(){
        guard.lock();
        timeLock.lock();
        System.out.println(statsObserver.toString());
        timeLock.unlock();
        guard.unlock();
    }

}