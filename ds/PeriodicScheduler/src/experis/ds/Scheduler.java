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
        var sleepCalculator = getSleepCalculator(sleepCalculatorType);
        schedule(operation, period, timeUnit, sleepCalculator);
    }

    public void schedule(Runnable operation, long period, TimeUnit timeUnit, SleepCalculator sleepCalculator) {
        if (operation == null) {
            return;
        }
        var list = threadsExtractor.get(operation);
        if (list == null) {
            list = new ArrayList<>();
            threadsExtractor.put(operation, list);
        }
        Task task = new Task(operation, period, timeUnit, sleepCalculator);
        Thread thread = new Thread(task);

        add(thread, task, list);
        thread.start();
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
        return (long cycle, long elapsed) -> Math.max(0, elapsed - cycle);

    }

    public void stop(Runnable operation) {
        List<Thread> threads = threadsExtractor.get(operation);
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

    public void shutDown() {
        for (var operation : threadsExtractor.keySet()) {
            stop(operation);
        }
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

    public void actionAll(TaskRunner<Runnable> taskRunner){
        for (var operation : threadsExtractor.keySet()) {
            taskRunner.apply(operation);
        }
    }

    public void action(Runnable operation, StatusChecker<Task> stateChecker, TaskRunner<Task> stateChanger){
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = taskExtractor.get(thread);
            if (stateChecker.apply(task)) {
                continue;
            }
            stateChanger.apply(task);
        }
    }

    public void reschedule(Runnable operation, long period, TimeUnit timeUnit) {
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = taskExtractor.get(thread);
            if (task.isFinished()) {
                continue;
            }
            task.setTime(timeUnit, period);
        }
    }

}

