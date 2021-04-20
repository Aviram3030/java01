package experis.ds;

public class TaskRunner implements Runnable{
    private Task task;
    private long period;

    public TaskRunner(Runnable runnable, long period){
        task = new Task(runnable);
        this.period = period;
    }
    @Override
    public void run() {
        try {
            while (true) {
                task.makeThread();
                task.start();
                Thread.sleep(period);
            }
        }catch (InterruptedException e) {
            return;
        }
    }

    public Task getTask(){
        return task;
    }

    public void setPeriod(long period){
        this.period = period;
    }
}
