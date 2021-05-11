package experis.ds;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeContainer<T> {
    private final ConcurrentHashMap<Thread, Queue<T>> container = new ConcurrentHashMap<>();
    private final Queue<Queue<T>> twoItemsOrMoreContainers = new LinkedList<>();
    private final Queue<Condition> waitingConditions = new LinkedList<>();
    private final Lock guard = new ReentrantLock();
    private final Lock getLock = new ReentrantLock();

    public void put(T item) {
        Thread thread = Thread.currentThread();

        guard.lock();
        Queue<T> queue = container.get(thread);
        if (queue == null) {
            queue = new LinkedList<>();
            container.put(thread, queue);
        }
        queue.add(item);

        checkSize(queue);
        guard.unlock();
    }

    private void checkSize(Queue<T> queue){
        if (queue.size() >= 2) {
            if(queue.size() == 2) {
                twoItemsOrMoreContainers.add(queue);
            }
            if (!waitingConditions.isEmpty()) {
                wakeNextWaitingThread();
            }
        }
    }

    private void wakeNextWaitingThread() {
        Condition active = waitingConditions.poll();
        active.signal();
    }

    public Pair<T> get() {
        getLock.lock();
        guard.lock();
        if (twoItemsOrMoreContainers.isEmpty()) {
            try {
                Condition active = guard.newCondition();
                waitingConditions.add(active);
                active.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Pair<T> pair = getPair();
        guard.unlock();
        getLock.unlock();
        return pair;
    }

    private Pair<T> getPair() {
        Queue<T> queue = twoItemsOrMoreContainers.peek();
        Pair<T> pair = getFirstTwoItems(queue);

        if (queue.size() < 2) {
            twoItemsOrMoreContainers.poll();
        }
        return pair;
    }

    private Pair<T> getFirstTwoItems(Queue<T> queue) {
        T first = queue.poll();
        T second = queue.poll();
        return new Pair<>(first, second);
    }
}
