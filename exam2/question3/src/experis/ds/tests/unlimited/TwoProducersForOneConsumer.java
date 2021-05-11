package experis.ds.tests.unlimited;

import experis.ds.ThreadSafeContainer;
import experis.ds.tests.Box;
import experis.ds.tests.Producer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoProducersToOneConsumer {
    ThreadSafeContainer<Box> queue = new ThreadSafeContainer<>();
    final int N = 1000;

    @Test
    void twoProducersToOneConsumer() {
        List<Box> firstProducts = createBoxList(0, N);
        List<Box> secondProducts = createBoxList(1, N / 2);

        Producer firstProducer = new Producer(firstProducts, queue);
        Producer secondProducer = new Producer(secondProducts, queue);
        Consumer consumer = new Consumer(queue);

        Thread firstProducerThread = new Thread(firstProducer);
        Thread secondProducerThread = new Thread(secondProducer);
        Thread consumerThread = new Thread(consumer);

        consumerThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        firstProducerThread.start();
        secondProducerThread.start();

        try {
            firstProducerThread.join();
            secondProducerThread.join();
            queue.put(new Box(-1, 0));
            queue.put(new Box(-1, 0));
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Box> result = consumer.getProducts();
        assertTrue(checkList(result));
    }

    private Boolean checkList(List<Box> list) {
        int firstMin = -1;
        int secondMin = -1;

        for(var box: list){
            if(box.getType() == 1){
                int val = box.getVal();
                if(val < firstMin){
                    return false;
                }
                else{
                    firstMin = val;
                }
            }
            else if(box.getType() == 2){
                int val = box.getVal();
                if(val < secondMin){
                    return false;
                }
                else{
                    secondMin = val;
                }
            }
        }
        return true;
    }

    List<Box> createBoxList(int type, int length){
        List<Box> list= new ArrayList<>();
        for(int i = 0; i < length; i++){
            list.add(new Box(i, type));
        }
        return list;
    }
}