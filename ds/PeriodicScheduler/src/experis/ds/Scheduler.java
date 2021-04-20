package experis.ds;

import java.util.HashMap;

class Pair{
    private final Thread thread;
    private final TaskRunner TaskRunner;

    public Pair(TaskRunner TaskRunner){
        this.TaskRunner = TaskRunner;
        this.thread = new Thread(TaskRunner);
    }

    public TaskRunner getTaskRunner() {
        return TaskRunner;
    }

    public Thread getThread() {
        return thread;
    }
}

public class Scheduler {
    private HashMap<Runnable,Pair> tasks = new HashMap<>();

    public void schedule(Runnable runnable, long period){
        if(runnable == null){
            return;
        }

        Pair pair = tasks.get(runnable);
        if(pair == null){
            TaskRunner taskRunner = new TaskRunner(runnable, period);
            tasks.put(runnable, new Pair(taskRunner));
        }
        pair.getThread().start();
    }

    public void stop(Runnable runnable){
        Pair pair = tasks.get(runnable);
        if(pair != null) {
            Thread thread = pair.getThread();
            thread.interrupt();
        }
    }

    public void stopAll(){
        for(var runnable: tasks.keySet()){
            stop(runnable);
        }
    }

    public void suspend(Runnable runnable){
        Pair pair = tasks.get(runnable);
        if(pair == null){
            return;
        }

        Thread thread = pair.getThread();
        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(Runnable runnable){
        Pair pair = tasks.get(runnable);
        if(pair == null){
            return;
        }

        Thread thread = pair.getThread();
        thread.notify();
    }

    public void reschedule(Runnable runnable, long period){
        Pair pair = tasks.get(runnable);
        TaskRunner taskRunner = pair.getTaskRunner();
        if(taskRunner == null){
            schedule(runnable, period);
        }
        taskRunner.setPeriod(period);
    }
}
