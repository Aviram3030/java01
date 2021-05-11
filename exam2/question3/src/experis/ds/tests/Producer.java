package experis.ds.tests;

import experis.ds.ThreadSafeContainer;

import java.util.List;

public class Producer implements Runnable{
    private final ThreadSafeContainer<Box> container;
    private final List<Box> products;

    public Producer(List<Box> products, ThreadSafeContainer<Box> container){
        this.container = container;
        this.products = products;
    }

    @Override
    public void run() {
        for (var item : products) {
            container.put(item);
        }
    }

}