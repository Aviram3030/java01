package experis.ds;

public class ThreadSafeQueue <T>{
    private final T[] data;
    private final Object lock = new Object();
    private int size = 0;
    private int head = 0; // index of the current front item, if one exists
    private int tail = 0; // index of next item to be added

    public ThreadSafeQueue(T[] data){
        this.data = data;
    }

    public void enqueue(T a){
        synchronized (lock){
            while(isFull()){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            data[tail] = a;
            tail = (tail + 1) % data.length;
            size++;
            lock.notify();
        }
    }

    public T dequeue(){
        T val;
        synchronized (lock){
            while(isEmpty()){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            val = data[head];
            data[head] = null;
            head = (head + 1) % data.length;
            size--;
            lock.notify();
        }
        return val;
    }

    public int size(){
        synchronized (lock) {
            return size;
        }
    }

    public Boolean isEmpty(){
        synchronized (lock) {
            return size == 0;
        }
    }

    public Boolean isFull(){
        synchronized (lock) {
            return size == data.length;
        }
    }
}
