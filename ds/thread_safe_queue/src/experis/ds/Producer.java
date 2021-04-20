package experis.ds;

import experis.ds.tests.Box;

import java.util.List;

public class Producer implements Runnable{
    private final ThreadSafeQueue<Box> queue;
    private final List<Box> products;

    public Producer(List<Box> products, ThreadSafeQueue<Box> queue){
        this.queue = queue;
        this.products = products;
    }

    @Override
    public void run() {
        for (var item : products) {
            queue.enqueue(item);
        }
    }
}
