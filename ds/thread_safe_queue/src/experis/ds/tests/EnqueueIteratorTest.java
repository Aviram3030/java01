package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EnqueueIteratorTest {

    @Test
    void enqueueIteratorTest() {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(1000);
        ArrayList<Integer> products = new ArrayList<>();

        for (int i = 0; i < 1500; i++) {
            products.add(i);
        }

        Consumer<Integer> consumer = new Consumer<>(new Integer[1000], queue);
        Thread thread = new Thread(consumer);

        thread.start();
        queue.enqueue(products.iterator());
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer[] result = consumer.getProducts();
        for(int i = 0; i < result.length; i++){
            assertEquals(result[i], products.get(i));
        }
        assertEquals(500, queue.size());
    }
}