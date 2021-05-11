package experis.ds.tests.limited;

import experis.ds.ThreadSafeContainer;
import experis.ds.tests.Box;
import experis.ds.tests.Producer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizationTest {
    ThreadSafeContainer<Box> container = new ThreadSafeContainer<>();
    final int N = 1000;

    @Test
    void synchronization_test() throws InterruptedException {
        List<Box> firstProducerProducts = createBoxList(0, N / 2);
        List<Box> secondProducerProducts = createBoxList(N / 2, N);
        LimitedConsumer[] consumers = new LimitedConsumer[N / 2];
        Thread[] consumerThreads = new Thread[N / 2];

        for(int i = 0; i < consumers.length; i++){
            consumers[i] = new LimitedConsumer(container, 2);
        }
        Producer firstProducer = new Producer(firstProducerProducts, container);
        Producer secondProducer = new Producer(secondProducerProducts, container);

        for(int i = 0; i < consumers.length; i++){
            consumerThreads[i] = new Thread(consumers[i]);
            consumerThreads[i].start();
            Thread.sleep(1);
        }

        Thread firstProducerThread = new Thread(firstProducer);
        Thread secondProducerThread = new Thread(secondProducer);
        firstProducerThread.start();
        secondProducerThread.start();

        for (Thread consumerThread : consumerThreads) {
            consumerThread.join();
        }

        firstProducerThread.join();
        secondProducerThread.join();

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