package experis.ds.userinterface;

import experis.ds.logic.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class TaskManager {
    private List<Observer> observerList = new ArrayList<>();
    private List<Future<Observer>> futures = new ArrayList<>();

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
