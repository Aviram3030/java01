package experis.ds.tests.unlimited;

import experis.ds.ThreadSafeContainer;
import experis.ds.tests.Box;
import experis.ds.tests.Producer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoConsumersToOneProducer {
    ThreadSafeContainer<Box> container = new ThreadSafeContainer<>();
    final int N = 2000;

    @Test
    void twoConsumersToOneProducer() throws InterruptedException {
        List<Box> products = createBoxList(0, N);

        Consumer firstConsumer = new Consumer(container);
        Consumer secondConsumer = new Consumer(container);
        Producer producer = new Producer(products, container);

        Thread firstConsumerThread = new Thread(firstConsumer);
        Thread secondConsumerThread = new Thread(secondConsumer);
        Thread producerThread = new Thread(producer);

        firstConsumerThread.start();
        secondConsumerThread.start();
        Thread.sleep(100);
        producerThread.start();

        try {
            producerThread.join();
            container.put(new Box(-1, 0));
            container.put(new Box(-1, 0));

            firstConsumerThread.join();
            secondConsumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Box> firstList = firstConsumer.getProducts();
        List<Box> secondList = secondConsumer.getProducts();
        assertTrue(checkLists(firstList, secondList));
    }

    Boolean checkLists(List<Box> firstList, List<Box> secondList) {
        int firstPointer = 0;
        int secondPointer = 0;
        int length = firstList.size() + secondList.size();
        for(int i = 0; i < length; i++){
            if(firstPointer != firstList.size() && firstList.get(firstPointer).getVal() == i){
                firstPointer++;
            }
            else if(secondPointer != secondList.size() && secondList.get(secondPointer).getVal() == i){
                secondPointer++;
            }
            else{
                return false;
            }
        }
        return true;
    }

    List<Box> createBoxList(int type, int length){
        List<Box> list = new ArrayList<>();
        for(int i = 0; i < length; i++){
            list.add(new Box(i, type));
        }

        return list;
    }
}