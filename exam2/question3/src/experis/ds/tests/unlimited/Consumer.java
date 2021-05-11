package experis.ds.tests.unlimited;

import experis.ds.Pair;
import experis.ds.ThreadSafeContainer;
import experis.ds.tests.Box;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable{
    private final ThreadSafeContainer<Box> container;
    private final List<Box> products = new ArrayList<>();

    public Consumer(ThreadSafeContainer<Box> container){
        this.container = container;
    }

    @Override
    public void run() {
        while(true){
            Pair<Box> pair = container.get();
            Box firstBox = pair.getFirst();
            Box secondBox = pair.getSecond();
            if(firstBox.getVal() == -1 || secondBox.getVal() == -1){
                container.put(firstBox);
                container.put(secondBox);
                return;
            }
            products.add(firstBox);
            products.add(secondBox);
        }
    }

    public List<Box> getProducts(){
        return products;
    }
}