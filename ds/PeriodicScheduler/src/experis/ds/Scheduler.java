package experis.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    private final HashMap<Runnable, List<Thread>> threadsExtractor = new HashMap<>();
    private final HashMap<Thread, Task> tasks = new HashMap<>();

    public void schedule(Runnable operation, long period, TimeUnit timeUnit) {
        if (operation == null) {
            return;
        }
        List<Thread> list = threadsExtractor.get(operation);
        if (list == null) {
            list = new ArrayList<>();
            threadsExtractor.put(operation, list);
        }
        Task task = new Task(operation, period, timeUnit);
        Thread thread = new Thread(task);

        add(thread, task, list);
        thread.start();
    }

    private void add(Thread thread, Task task, List<Thread> list){
        tasks.put(thread, task);
        list.add(thread);
    }

    public void stop(Runnable operation) {
        StateChecker<Task> stateChecker = Task::isSuspended;
        StateChanger<Task> stateChanger = Task::stop;
        action(operation, stateChecker, stateChanger);
        /*
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (task.isSuspended()) {
                task.resume();
            }
            task.stop();
        } */
    }

    public void shutDown() {
        for (var runnable : threadsExtractor.keySet()) {
            stop(runnable);
        }
    }

    public void suspend(Runnable operation) {
        StateChecker<Task> stateChecker = (Task task) -> !task.isRunning();
        StateChanger<Task> stateChanger = Task::suspend;
        action(operation, stateChecker, stateChanger);
        /*
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (!task.isRunning()) {
                continue;
            }
            task.suspend();
        }*/
    }

    public void resume(Runnable operation) {
        StateChecker<Task> stateChecker = (Task task) -> !task.isSuspended();
        StateChanger<Task> stateChanger = Task::resume;
        action(operation, stateChecker, stateChanger);

        /* List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (!task.isSuspended()) {
                continue;
            }
            task.resume();
        }*/
    }

    public void action(Runnable operation, StateChecker<Task> stateChecker, StateChanger<Task> stateChanger){

        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            stateChecker.apply(task);
            if (stateChecker.apply(task)) {
                continue;
            }
            stateChanger.apply(task);
        }
    }


    public void suspendAll(){
        for (var runnable : threadsExtractor.keySet()) {
            suspend(runnable);
        }
    }

    public void resumeAll(){
        for (var runnable : threadsExtractor.keySet()) {
            resume(runnable);
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

