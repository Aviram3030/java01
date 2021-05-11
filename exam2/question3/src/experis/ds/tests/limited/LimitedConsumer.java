package experis.ds.tests.limited;

import experis.ds.Pair;
import experis.ds.ThreadSafeContainer;
import experis.ds.tests.Box;

import java.util.ArrayList;
import java.util.List;

public class LimitedConsumer implements Runnable{
    private final ThreadSafeContainer<Box> container;
    private final List<Box> products = new ArrayList<>();
    private final int limit;

    public LimitedConsumer(ThreadSafeContainer<Box> container, int limit) {
        this.container = container;
        this.limit = limit;
    }

    @Override
    public void run() {
        while (products.size() < limit) {
            Pair<Box> pair = container.get();
            Box firstBox = pair.getFirst();
            Box secondBox = pair.getSecond();

            products.add(firstBox);
            products.add(secondBox);
        }
    }

    public List<Box> getProducts() {
        return products;
    }

}
