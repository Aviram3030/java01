package experis.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    private HashMap<Runnable, List<Thread>> threadsExtractor = new HashMap<>();
    private HashMap<Thread, Task> tasks = new HashMap<>();

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

        tasks.put(thread, task);
        list.add(thread);
        thread.start();
    }

    public void stop(Runnable operation) {
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (task.getState() == State.SUSPENDED) {
                threads.notify();
            }
            task.setState(State.FINISHED);
            task.stop();
        }
    }

    public void shutDown() {
        for (var runnable : threadsExtractor.keySet()) {
            stop(runnable);
        }
    }

    public void suspend(Runnable operation) {
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (task.getState() != State.RUNNING) {
                continue;
            }

            task.setState(State.SUSPENDED);
        }
    }

    public void resume(Runnable operation) {
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (task.getState() != State.SUSPENDED) {
                continue;
            }

            task.setState(State.RUNNING);
            task.resume();
        }
    }

    public void reschedule(Runnable operation, long period, TimeUnit timeunit) {
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            if (task.getState() == State.FINISHED) {
                continue;
            }

            task.setPeriod(period);
            task.setTimeUnit(timeunit);
        }
    }


    private void action(Runnable operation, Func func) {
        List<Thread> threads = threadsExtractor.get(operation);
        for (var thread : threads) {
            Task task = tasks.get(thread);
            func.apply();
        }
    }
}

