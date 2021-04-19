package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TwoProducersToOneConsumer {

    @Test
    void twoProducersToOneConsumer() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(new Integer[2000]);
        Integer[] products = new Integer[1000];

        for (int i = 0; i < products.length; i++) {
            products[i] = i;
        }

        Producer<Integer> firstProducer = new Producer<>(products, queue);
        Producer<Integer> secondProducer = new Producer<>(products, queue);
        Consumer<Integer> consumer = new Consumer<>(new Integer[2000], queue);

        Thread firstProducerThread = new Thread(firstProducer);
        Thread secondProducerThread = new Thread(secondProducer);
        Thread consumerThread = new Thread(consumer);

        consumerThread.start();
        Thread.sleep(100);
        firstProducerThread.start();
        secondProducerThread.start();

        try {
            firstProducerThread.join();
            consumerThread.join();
            secondProducerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer[] arr = consumer.getProducts();
        assertTrue(checkArray(arr));
        assertEquals(0, queue.size());
    }

    private Boolean checkArray(Integer[] arr) {
        int max = 0;
        for(Integer num: arr){
            if(max + 1 < num){
                return false;
            }
            else if(max + 1 == num){
                max = num;
            }
        }

        return true;
    }
}