package experis.ds;

import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

public class ThreadSafeQueue <T>{
    private final T[] data;
    //private final Object lock = new Object();
    //private int enqueueWaiting = 0;
    //private int dequeueWaiting = 0;
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition haveSomeItems = lock.newCondition();
    private final Condition haveSomeVacancies = lock.newCondition();

    public ThreadSafeQueue(int capacity){
        data = (T[]) new Object[capacity];
    }

    public void enqueue(T val){
        lock.lock();
        try {
            waitWhile(this::full, haveSomeVacancies);
            insert(val);
            haveSomeItems.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public T dequeue(){
        T val;
        lock.lock();
        try {
            waitWhile(this::empty, haveSomeItems);
            val = remove();
            haveSomeVacancies.signal();
        }
        finally {
            lock.unlock();
        }
        return val;
    }

    private T remove(){
        T val = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;

        return val;
    }

    private void insert(T val){
        data[tail] = val;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void waitWhile(BooleanSupplier waitReason, Condition condition){
        while(waitReason.getAsBoolean()){
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enqueue(Iterator<T> iterator){
        lock.lock();
        while (iterator.hasNext()) {
            enqueue(iterator.next());
        }
        lock.unlock();
    }

    public int size(){
        synchronized (lock) {
            return size;
        }
    }

    public Boolean isEmpty(){
        lock.lock();
        Boolean present = empty();
        lock.unlock();
        return present;
    }

    private Boolean empty(){
        return size == 0;
    }

    public Boolean isFull(){
        lock.lock();
        Boolean present = full();
        lock.unlock();
        return present;
    }

    private Boolean full(){
        return size == data.length;
    }
}
