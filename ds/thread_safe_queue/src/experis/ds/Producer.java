package experis.ds;

public class Producer<T> implements Runnable{
    ThreadSafeQueue<T> queue;
    T[] products;

    public Producer(T[] products, ThreadSafeQueue<T> queue){
        this.queue = queue;
        this.products = products;
    }

    @Override
    public void run() {
        for (T product : products) {
            queue.enqueue(product);
        }
    }
}
