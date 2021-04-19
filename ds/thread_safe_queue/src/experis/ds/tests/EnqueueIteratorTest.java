package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnqueueIteratorTest {

    @Test
    void enqueueIteratorTest() {
        ThreadSafeQueue<Box> queue = new ThreadSafeQueue<>(100);
        List<Box> products;

        final int N = 2000;
        products = createBoxList(0, N);

        Consumer consumer = new Consumer(queue);
        Thread thread = new Thread(consumer);

        thread.start();
        queue.enqueue(products.iterator());
        queue.enqueue(new Box(-1, 1));
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Box> result = consumer.getProducts();
        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i), products.get(i));
        }
        assertEquals(1, queue.size());
    }

    List<Box> createBoxList(int index, int length) {
        List<Box> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(new Box(i, index));
        }
        return list;
    }
}