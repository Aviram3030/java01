package experis.ds.tests.limited;


import experis.ds.ThreadSafeContainer;
import experis.ds.tests.Box;
import experis.ds.tests.Producer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ManyToManyTest {
    ThreadSafeContainer<Box> container = new ThreadSafeContainer<>();
    final int N = 1000;
    final int NUM_OF_PRODUCERS = 100;

    @Test
    void synchronization_test() throws InterruptedException {
        List<List<Box>> producersProducts = new ArrayList<>();
        List<Producer> producers = new ArrayList<>();
        List<Thread> producersThreads = new ArrayList<>();

        int numOfProducts = N / NUM_OF_PRODUCERS;
        for(int i = 0; i < N; i = i + numOfProducts){
            producersProducts.add(createBoxList(i, i + numOfProducts));
        }

        LimitedConsumer[] consumers = new LimitedConsumer[N / 2];
        Thread[] consumerThreads = new Thread[N / 2];
        for(int i = 0; i < consumers.length; i++){
            consumers[i] = new LimitedConsumer(container, 2);
        }

        for(int i = 0; i < NUM_OF_PRODUCERS; i++) {
            producers.add(new Producer(producersProducts.get(i), container));
        }

        for(int i = 0; i < consumers.length; i++){
            consumerThreads[i] = new Thread(consumers[i]);
            consumerThreads[i].start();
            Thread.sleep(1);
        }

        for(int i = 0; i < NUM_OF_PRODUCERS; i++) {
            Thread producersThread = new Thread(producers.get(i));
            producersThread.start();
            producersThreads.add(producersThread);
            Thread.sleep(1);
        }

        for (Thread consumerThread : consumerThreads) {
            consumerThread.join();
        }

        for(int i = 0; i < NUM_OF_PRODUCERS; i++) {
            producersThreads.get(i).join();
        }

        assertTrue(checkResults(consumers));
    }

    boolean checkResults(LimitedConsumer[] consumers) {
        for(int i = 0; i < consumers.length; i++){
            List<Box> result = consumers[i].getProducts();
            if(result.size() != 2){
                return false;
            }

            int firstResult = result.get(0).getVal();
            int secondResult = result.get(1).getVal();

            if(firstResult != i * 2 || secondResult != (i * 2 + 1)){
                return false;
            }
        }

        return true;
    }

    List<Box> createBoxList(int start, int end){
        List<Box> list = new ArrayList<>();
        for(int i = start; i < end; i++){
            list.add(new Box(i, 0));
        }
        return list;
    }
}