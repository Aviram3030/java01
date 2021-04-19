package experis.ds;

import experis.ds.tests.Box;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable{
    private final ThreadSafeQueue<Box> queue;
    private final List<Box> products = new ArrayList<>();

    public Consumer(ThreadSafeQueue<Box> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            Box box = queue.dequeue();
            if(box.getVal() == -1){
                queue.enqueue(box);
                return;
            }
            products.add(box);
        }
    }

    public List<Box> getProducts(){
        return products;
    }
}
