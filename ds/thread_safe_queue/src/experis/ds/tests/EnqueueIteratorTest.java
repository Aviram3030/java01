package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnqueueIteratorTest {

    ThreadSafeQueue<Box> queue = new ThreadSafeQueue<>(10);
    final int N = 2000;
    List<Box> products = new ArrayList<>();

    @Test
    void enqueueIteratorTest() {
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
}