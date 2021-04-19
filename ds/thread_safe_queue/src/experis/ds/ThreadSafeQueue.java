package experis.ds;

import java.util.Iterator;

public class ThreadSafeQueue <T>{
    private final T[] data;
    private final Object firstLock = new Object();
    private final Object secondLock = new Object();
    private final Object thirdLock = new Object();
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    public ThreadSafeQueue(int capacity){
        data = (T[]) new Object[capacity];
    }

    public void enqueue(T a){
        synchronized (firstLock){
            while(isFull()){
                try {
                    firstLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            data[tail] = a;
            tail = (tail + 1) % data.length;
            size++;
            firstLock.notifyAll();
        }
    }

    public void enqueue(Iterator<T> iterator){
        while (iterator.hasNext()) {
            enqueue(iterator.next());
        }
    }

    public T dequeue(){
        T val;
        synchronized (firstLock){
            while(isEmpty()){
                try {
                    firstLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            val = data[head];
            data[head] = null;
            head = (head + 1) % data.length;
            size--;
            firstLock.notifyAll();
        }
        return val;
    }

    public int size(){
        synchronized (firstLock) {
            return size;
        }
    }

    public Boolean isEmpty(){
        synchronized (firstLock) {
            return size == 0;
        }
    }

    public Boolean isFull(){
        synchronized (firstLock) {
            return size == data.length;
        }
    }
}
