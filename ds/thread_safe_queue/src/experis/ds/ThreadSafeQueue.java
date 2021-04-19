package experis.ds;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;

public class ThreadSafeQueue <T>{
    private final T[] data;
    private final Object firstLock = new Object();
    private static AtomicInteger enqueueWaiting = new AtomicInteger();
    private static AtomicInteger dequeueWaiting = new AtomicInteger();
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    public ThreadSafeQueue(int capacity){
        data = (T[]) new Object[capacity];
    }

    public void enqueue(T a){
        synchronized (firstLock){
            enqueueWaiting.incrementAndGet();
            waitWhile(this::full);
            enqueueWaiting.decrementAndGet();

            data[tail] = a;
            tail = (tail + 1) % data.length;
            size++;

            if(dequeueWaiting.get() != 0){
                firstLock.notify();
            }
        }
    }

    public T dequeue(){
        T val;
        synchronized (firstLock){
            dequeueWaiting.incrementAndGet();
            waitWhile(this::empty);
            dequeueWaiting.decrementAndGet();

            val = data[head];
            data[head] = null;
            head = (head + 1) % data.length;
            size--;
            if(enqueueWaiting.get() != 0){
                firstLock.notify();
            }
        }
        return val;
    }

    private void waitWhile(BooleanSupplier waitReason){
        while(waitReason.getAsBoolean()){
            try {
                firstLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enqueue(Iterator<T> iterator){
        while (iterator.hasNext()) {
            enqueue(iterator.next());
        }
    }

    public int size(){
        synchronized (firstLock) {
            return size;
        }
    }

    public Boolean isEmpty(){
        return size == 0;
    }

    private Boolean empty(){
        synchronized (firstLock) {
            return isEmpty();
        }
    }

    public Boolean isFull(){
        return size == data.length;
    }

    private Boolean full(){
        synchronized (firstLock) {
            return isFull();
        }
    }
}
