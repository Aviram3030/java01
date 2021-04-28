package experis.ds.userinterface;

import experis.ds.logic.MovieCenter;
import experis.ds.logic.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskManager {
    private List<Observer> observerList = new ArrayList<>();
    private List<Future<Observer>> futures = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public void execute(String data){
        Future<Observer> future = executor.submit(new MovieCenter(4, data));
        futures.add(future);
    }

    public void checkFuture() throws ExecutionException, InterruptedException {
        for (Future<Observer> observerFuture : futures) {
            Future<Observer> future = observerFuture;
            if (future.isDone()) {
                observerList.add(future.get());
            }
        }
    }

    public void addFuture(Future<Observer> future){
        futures.add(future);
    }

    public void addObserver(Observer observer){
        observerList.add(observer);
    }

    public void removeFuture(int index){
        futures.remove(index);
    }

    public void removeObserver(int index){
        observerList.remove(index);
    }

    public Observer getObserver(int index){
        return observerList.get(index);
    }

    public Future<Observer> getFuture(int index){
        return futures.get(index);
    }
}
