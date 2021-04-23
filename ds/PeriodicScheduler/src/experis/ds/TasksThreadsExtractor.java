package experis.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TasksThreadsExtractor {
    private final ConcurrentHashMap<Runnable, List<Thread>> threadsExtractor = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Thread, Task> tasksExtractor = new ConcurrentHashMap<>();

    public Task getTask(Thread thread){
        return tasksExtractor.get(thread);
    }

    public List<Thread> getThreads(Runnable operation){
        return threadsExtractor.get(operation);
    }

    public void add(Task task,Thread thread){
        tasksExtractor.put(thread, task);
    }

    public void add(Runnable operation, Thread thread){
        List<Thread> threads = threadsExtractor.get(operation);
        if(threads == null){
            threads = new ArrayList<>();
            threadsExtractor.put(operation, threads);
        }
        threads.add(thread);
    }
}
