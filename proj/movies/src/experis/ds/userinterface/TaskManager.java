package experis.ds.userinterface;

import experis.ds.domainentities.Movie;
import experis.ds.logic.MovieCenter;
import experis.ds.logic.Observer;
import experis.ds.userinterface.output.Display;
import experis.ds.userinterface.output.DisplayConsole;

import java.util.concurrent.*;

public class TaskManager {
    private ConcurrentHashMap<String,Observer> observers = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,Future<Observer>> futures = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final DisplayConsole<Movie> displayConsole = new DisplayConsole<>();

    public void execute(String query) {
        var movieCenter = new MovieCenter(4, query, displayConsole);
        Future<Observer> future = executor.submit(movieCenter);
        futures.put(query, future);
    }

    public void checkFuture() throws ExecutionException, InterruptedException {
        for (String query : futures.keySet()) {
            Future<Observer> future = futures.get(query);
            if (future.isDone()) {
                observers.put(query, future.get());
                futures.remove(future);
                System.out.printf("The Query for the title '%s' is done\n", query);
            }
        }
    }

    public void stop(String query) {
        Future<Observer> future = futures.get(query);
        future.cancel(true);
    }

    public void displayObservers(Display display){

    }

    public Observer getObserver(String query) {
        return observers.get(query);
    }
}
