package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TwoProducersToOneConsumer {

    @Test
    void twoProducersToOneConsumer() throws InterruptedException {
        ThreadSafeQueue<Box> queue = new ThreadSafeQueue<>(100);
        final int N = 1000;

        List <Box> firstProducts = createBoxList(0, N);
        List <Box> secondProducts = createBoxList(1, N / 2);

        Producer firstProducer = new Producer(firstProducts, queue);
        Producer secondProducer = new Producer(secondProducts, queue);
        Consumer consumer = new Consumer(queue);

        Thread firstProducerThread = new Thread(firstProducer);
        Thread secondProducerThread = new Thread(secondProducer);
        Thread consumerThread = new Thread(consumer);

        consumerThread.start();
        Thread.sleep(100);
        firstProducerThread.start();
        secondProducerThread.start();

        try {
            firstProducerThread.join();
            secondProducerThread.join();
            queue.enqueue(new Box(-1,0));
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
            if(box.getIndex() == 1){
                int val = box.getVal();
                if(val < firstMin){
                    return false;
                }
                else{
                    firstMin = val;
                }
            }
            else if(box.getIndex() == 2){
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

    List<Box> createBoxList(int index, int length){
        List<Box> list= new ArrayList<>();
        for(int i = 0; i < length; i++){
            list.add(new Box(i, index));
        }

        return list;
    }
}