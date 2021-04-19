package experis.ds.tests;

import java.util.function.Supplier;

public class ThreadGroup {
    private final Thread[] threads;
    private final Runnable[] runners;

    public ThreadGroup(int n, Supplier<Runnable> runnableFactory) {
        threads = new Thread[n];
        runners = new Runnable[n];

        for (int i = 0; i < threads.length; i++) {
            runners[i] = runnableFactory.get();
            threads[i] = new Thread(runners[i]);
        }
    }

    public void start(){
        for (var t : threads) {
            t.start();
        }
    }

    public void join(){
        for (var t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Runnable get(int i){
        return runners[i];
    }

}
