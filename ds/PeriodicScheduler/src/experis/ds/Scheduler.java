package experis.ds;

import experis.ds.lmbda.SleepCalculator;
import experis.ds.lmbda.TaskRunner;
import experis.ds.lmbda.StatusChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final ConcurrentHashMap<Runnable, List<Thread>> threadsExtractor = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Thread, Task> taskExtractor = new ConcurrentHashMap<>();

    public void schedule(Runnable operation, long period, TimeUnit timeUnit, SleepCalculatorType sleepCalculatorType) {
        if (isNull(operation) || isNull(sleepCalculatorType)) {
            return;
        }
        var sleepCalculator = getSleepCalculator(sleepCalculatorType);
        schedule(operation, period, timeUnit, sleepCalculator);
    }

    public void schedule(Runnable operation, long period, TimeUnit timeUnit, SleepCalculator sleepCalculator) {
        if (checkIsIllegal(operation, period, timeUnit, sleepCalculator)) {
            return;
        }
        var list = threadsExtractor.get(operation);
        if (list == null) {
            list = new ArrayList<>();
            threadsExtractor.put(operation, list);
        }
        Task task = new Task(operation,sleepCalculator,timeUnit.toNanos(period));
        Thread thread = new Thread(task);

        add(thread, task, list);
        thread.start();
    }

    private Boolean checkIsIllegal(Runnable operation, long period, TimeUnit timeUnit, SleepCalculator sleepCalculator){
        return (isNull(operation) || isNull(sleepCalculator) || isNull(timeUnit) || period < 0);
    }

    private void add(Thread thread, Task task, List<Thread> list){
        taskExtractor.put(thread, task);
        list.add(thread);
    }

    private SleepCalculator getSleepCalculator(SleepCalculatorType sleepCalculatorType){
        if(sleepCalculatorType == SleepCalculatorType.DELAY){
            return (long cycle, long elapsed) -> {
                long num = (cycle - elapsed) % cycle;
                if(num > 0) {
                    return num;
                }
                return num + cycle;
            };
        }
        return (long cycle, long elapsed) -> Math.max(0, cycle - elapsed);
    }

    public void shutDown() {
        for (var operation : threadsExtractor.keySet()) {
            stop(operation);
        }
    }

    public void stop(Runnable operation) {
        List<Thread> threads = getThreads(operation);
        if(isNull(threads)){
            return;
        }
        for (var thread : threads) {
            Task task = taskExtractor.get(thread);
            if (task.isFinished()) {
                continue;
            }
            task.stop();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            taskExtractor.remove(thread);
        }
        threadsExtractor.remove(operation);
    }

    public void suspendAll(){
        actionAll(this::suspend);
    }

    public void suspend(Runnable operation) {
        StatusChecker<Task> stateChecker = (Task task) -> !task.isRunning();
        TaskRunner<Task> stateChanger = Task::suspend;
        action(operation, stateChecker, stateChanger);
    }

    public void resumeAll(){
        actionAll(this::resume);
    }

    public void resume(Runnable operation) {
        StatusChecker<Task> stateChecker = (Task task) -> !task.isSuspended();
        TaskRunner<Task> stateChanger = Task::resume;
        action(operation, stateChecker, stateChanger);
    }

    public void schedulesInfo(){
        actionAll(this::scheduleInfo);
    }

    private void scheduleInfo(Runnable operation){
        StatusChecker<Task> stateChecker = (Task task) -> false;
        TaskRunner<Task> stateChanger = Task::printScheduleInfo;
        action(operation, stateChecker, stateChanger);
    }

    private void actionAll(TaskRunner<Runnable> taskRunner){
        for (var operation : threadsExtractor.keySet()) {
            taskRunner.apply(operation);
        }
    }

    private void action(Runnable operation, StatusChecker<Task> stateChecker, TaskRunner<Task> stateChanger){
        if(isNull(operation)){
            return;
        }

        List<Thread> threads = getThreads(operation);
        if(isNull(threads)){
            return;
        }
        for (var thread : threads) {
            Task task = taskExtractor.get(thread);
            if (stateChecker.apply(task)) {
                continue;
            }
            stateChanger.apply(task);
        }
    }

    public void reschedule(Runnable operation, long period, TimeUnit timeUnit) {
        List<Thread> threads = getThreads(operation);
        if(isNull(threads)){
            return;
        }
        for (var thread : threads) {
            Task task = taskExtractor.get(thread);
            task.setTime(timeUnit.toNanos(period));
        }
    }

    private List<Thread> getThreads(Runnable operation){
        if(isNull(operation)){
            return null;
        }
        return threadsExtractor.get(operation);
    }

    private <T> Boolean isNull(T data){
        return data == null;
    }
}

