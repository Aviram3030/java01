package experis.ds.userinterface;

import experis.ds.logic.MovieCenter;
import experis.ds.logic.Observer;

import java.util.concurrent.*;

public class TaskManager {
    private ConcurrentHashMap<String, Observer> observerList = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Future<Observer>> futures = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public void execute(String query){
        var movieCenter = new MovieCenter(4, query);
        Future<Observer> future = executor.submit(movieCenter);
        futures.put(query, future);
    }

    public void checkFuture() throws ExecutionException, InterruptedException {
        for (String query : futures.keySet()) {
            Future<Observer> future = futures.get(query);
            if (future.isDone()) {
                observerList.put(query, future.get());
                futures.remove(future);
                System.out.printf("The Query for the title '%s' is done\n", query);
            }
        }
    }

    public void stop(String query){
        Future<Observer> future = futures.get(query);
        future.cancel(true);
    }

    public Observer getObserver(String query){
        return observerList.get(query);
    }
}
