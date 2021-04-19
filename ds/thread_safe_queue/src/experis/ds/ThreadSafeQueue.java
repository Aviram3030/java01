package experis.ds;

import java.util.Iterator;
import java.util.function.BooleanSupplier;

public class ThreadSafeQueue <T>{
    private final T[] data;
    private final Object lock = new Object();
    private int enqueueWaiting = 0;
    private int dequeueWaiting = 0;
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    public ThreadSafeQueue(int capacity){
        data = (T[]) new Object[capacity];
    }

    public void enqueue(T a){
        synchronized (lock){
            ++enqueueWaiting;
            waitWhile(this::full);
            --enqueueWaiting;

            data[tail] = a;
            tail = (tail + 1) % data.length;
            size++;

            if(dequeueWaiting != 0){
                lock.notify();
            }
        }
    }

    public T dequeue(){
        T val;
        synchronized (lock){
            ++dequeueWaiting;
            waitWhile(this::empty);
            --dequeueWaiting;

            val = data[head];
            data[head] = null;
            head = (head + 1) % data.length;
            size--;
            if(enqueueWaiting != 0){
                lock.notify();
            }
        }
        return val;
    }

    private void waitWhile(BooleanSupplier waitReason){
        while(waitReason.getAsBoolean()){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enqueue(Iterator<T> iterator){
        synchronized (lock) {
            while (iterator.hasNext()) {
                enqueue(iterator.next());
            }
        }
    }

    public int size(){
        synchronized (lock) {
            return size;
        }
    }

    public Boolean isEmpty(){
        synchronized (lock) {
            return empty();
        }
    }

    private Boolean empty(){
        return size == 0;
    }

    public Boolean isFull(){
        synchronized (lock) {
            return full();
        }
    }

    private Boolean full(){
        return size == data.length;
    }
}
