package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoConsumersToOneProducer {

    @Test
    void twoConsumersToOneProducer() throws InterruptedException {
        ThreadSafeQueue<Box> queue = new ThreadSafeQueue<>(10);
        final int N = 2000;

        List<Box> products = createBoxList(0, N);

        Consumer firstConsumer = new Consumer(queue);
        Consumer secondConsumer = new Consumer(queue);
        Producer producer = new Producer(products, queue);

        Thread firstConsumerThread = new Thread(firstConsumer);
        Thread secondConsumerThread = new Thread(secondConsumer);
        Thread producerThread = new Thread(producer);

        firstConsumerThread.start();
        secondConsumerThread.start();
        Thread.sleep(100);
        producerThread.start();

        try {
            producerThread.join();
            queue.enqueue(new Box(-1, 0));
            firstConsumerThread.join();
            secondConsumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Box> firstList = firstConsumer.getProducts();
        List<Box> secondList = secondConsumer.getProducts();
        assertTrue(checkLists(firstList, secondList));

    }

    private Boolean checkLists(List<Box> firstList, List<Box> secondList) {
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

    List<Box> createBoxList(int index, int length){
        List<Box> list= new ArrayList<>();
        for(int i = 0; i < length; i++){
            list.add(new Box(i, index));
        }

        return list;
    }
}