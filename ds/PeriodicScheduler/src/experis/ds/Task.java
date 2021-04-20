package experis.ds;

public class Task{
    private Thread thread;
    private final Runnable runnable;

    public Task(Runnable runnable){
        this.runnable = runnable;
    }

    public void makeThread(){
        thread = new Thread(runnable);
    }

    public void start(){
        thread.start();
    }

    public void stop() {
        thread.stop();
    }
}
