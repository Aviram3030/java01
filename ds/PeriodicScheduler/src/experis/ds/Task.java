package experis.ds;

import experis.ds.lmbda.SleepCalculator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Task implements Runnable {
    private final TimeUnit sleepForNanos = TimeUnit.NANOSECONDS;
    private final Runnable operation;
    private long period;
    private Status status = Status.RUNNING;
    private TimeUnit timeUnit;
    private final Lock guard = new ReentrantLock(true);
    private final Lock lock = new ReentrantLock(true);
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
            guard.unlock();

            lock.lock();
            execute();
            lock.unlock();
        }
    }

    private void execute(){
        long start = System.nanoTime();
        operation.run();
        long elapsedTime = System.nanoTime() - start;
        sleep(elapsedTime);
    }

    private void sleep(long elapsedTime) {
        long cycle = timeUnit.toNanos(period);
        long timeToSleep = sleepCalculator.calculate(cycle, elapsedTime);
        try {
            sleepForNanos.sleep(timeToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void resume() {
        guard.lock();
        status = Status.RUNNING;
        active.signal();
        guard.unlock();
    }

    public void suspend() {
        guard.lock();
        status = Status.SUSPENDED;
        guard.unlock();
    }

    public void stop() {
        guard.lock();
        resume();
        status = Status.FINISHED;
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

    public void setTime(TimeUnit timeUnit, long period) {
        lock.lock();
        this.period = period;
        this.timeUnit = timeUnit;
        lock.unlock();
    }
}
