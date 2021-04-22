package experis.ds;

import experis.ds.lmbda.SleepCalculator;
import experis.ds.lmbda.StateChanger;
import experis.ds.lmbda.StateChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final ConcurrentHashMap<Runnable, List<Thread>> threadsExtractor = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Thread, Task> tasks = new ConcurrentHashMap<>();

    public void schedule(Runnable operation, long period, TimeUnit timeUnit, SleepCalculatorType sleepCalculatorType) {
        var sleepCalculator = getSleepCalculator(sleepCalculatorType);
        schedule(operation, period, timeUnit, sleepCalculator);
    }

    public void schedule(Runnable operation, long period, TimeUnit timeUnit, SleepCalculator sleepCalculator) {
        if (operation == null) {
            return;
        }
        List<Thread> list = threadsExtractor.get(operation);
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
        tasks.put(thread, task);
        list.add(thread);
    }

    private SleepCalculator getSleepCalculator(SleepCalculatorType sleepCalculatorType){
        if(sleepCalculatorType == SleepCalculatorType.DELAY){
            return (long cycle, long elapsed) -> (cycle - elapsed) % cycle;
        }
        return (long cycle, long elapsed) -> Math.max(0, elapsed - cycle);

    }

    public void stop(Runnable operation) {
        StateChecker<Task> stateChecker = Task::isFinished;
        StateChanger<Task> stateChanger = Task::stop;
        action(operation, stateChecker, stateChanger);
    }

    public void shutDown() {
        for (var operation : threadsExtractor.keySet()) {
            stop(operation);
        }
    }

    public void suspend(Runnable operation) {
        StateChecker<Task> stateChecker = (Task task) -> !task.isRunning();
        StateChanger<Task> stateChanger = Task::suspend;
        action(operation, stateChecker, stateChanger);
    }

    public void resume(Runnable operation) {
        StateChecker<Task> stateChecker = (Task task) -> !task.isSuspended();
        StateChanger<Task> stateChanger = Task::resume;
        action(operation, stateChecker, stateChanger);
    }

    public void action(Runnable operation, StateChecker<Task> stateChecker, StateChanger<Task> stateChanger){
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (stateChecker.apply(task)) {
                continue;
            }
            stateChanger.apply(task);
        }
    }

    public void suspendAll(){
        for (var operation : threadsExtractor.keySet()) {
            suspend(operation);
        }
    }

    public void resumeAll(){
        for (var operation : threadsExtractor.keySet()) {
            resume(operation);
        }
    }

    public void reschedule(Runnable operation, long period, TimeUnit timeunit) {
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (task.isFinished()) {
                continue;
            }
            task.setPeriod(period);
            task.setTimeUnit(timeunit);
        }
    }

}

