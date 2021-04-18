package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneToOneTest {

    @Test
    void oneToOne() {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(new Integer[1000]);
        Integer[] products = new Integer[1000];

        for(int i = 0; i < products.length; i++){
            products[i] = i;
        }
        Producer<Integer> producer = new Producer<>(products, queue);
        Consumer<Integer> consumer = new Consumer<>(new Integer[1000], queue);

        Thread produceThread = new Thread(producer);
        Thread consumeThread = new Thread(consumer);

        produceThread.start();
        consumeThread.start();

        try {
            produceThread.join();
            consumeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer[] result = consumer.getProducts();
        for(int i = 0; i < products.length; i++){
            assertEquals(i, result[i]);
        }
    }
}