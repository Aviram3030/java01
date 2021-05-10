package experis.ds;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeContainer<T> {
    private final ConcurrentHashMap<Thread, List<T>> container = new ConcurrentHashMap<>();
    private final HashSet<Thread> twoItemsContainers = new HashSet<>();
    private final HashSet<Condition> waitingThreads = new HashSet<>();
    private final Lock guard = new ReentrantLock();

    public void put(T... items) {
        Thread thread = Thread.currentThread();

        guard.lock();
        List<T> list = container.get(thread);
        if (list == null) {
            list = new ArrayList<>();
        }

        list.addAll(Arrays.asList(items));
        container.put(thread, list);

        if (list.size() > 2) {
            twoItemsContainers.add(thread);
            if (waitingThreads.size() != 0) {
                getNextWaitingThread();
            }
        }
        guard.unlock();

    }

    private void getNextWaitingThread() {
        Iterator<Condition> iterator = waitingThreads.iterator();
        Condition condition = iterator.next();
        waitingThreads.remove(condition);
        condition.signal();
    }

    public Pair<T> get() {
        guard.lock();
        if (twoItemsContainers.isEmpty()) {
            try {
                Thread thread = Thread.currentThread();
                waitingThreads.add(guard.newCondition());
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Pair<T> pair = getPair();
        guard.unlock();
        return pair;
    }

    private Pair<T> getPair() {
        Iterator<Thread> iterator = twoItemsContainers.iterator();
        Thread thread = iterator.next();
        List<T> list = container.get(thread);

        Pair<T> pair = getFirstTwoItems(list);

        if (list.size() < 2) {
            twoItemsContainers.remove(thread);
        }
        return pair;
    }

    private Pair<T> getFirstTwoItems(List<T> list) {
        T first = list.get(0);
        T second = list.get(1);
        Pair<T> pair = new Pair<>(first, second);

        list.remove(first);
        list.remove(second);

        return pair;
    }
}