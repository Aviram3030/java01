package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OneToOneTest {

    @Test
    void oneToOne() {
        var queue = new ThreadSafeQueue<Box>(100);
        final int N = 1000;

        List<Box> products = createBoxList(0, N);
        Producer producer = new Producer(products, queue);
        Consumer consumer = new Consumer(queue);

        Thread produceThread = new Thread(producer);
        Thread consumeThread = new Thread(consumer);

        produceThread.start();
        consumeThread.start();

        try {
            produceThread.join();
            queue.enqueue(new Box(-1,0));
            consumeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.enqueue(new Box(-1, 0));

        /* ArrayList<Box> result = consumer.getProducts();
        compareLists(products, result);
        assertEquals(0, queue.size()); */
    }

    void compareLists(List<Box> products, List<Box> result){
        assertEquals(products.size(), result.size());
        for(int i = 0; i < products.size(); i++){
            assertEquals(products.get(i).getVal(), result.get(i).getVal());
        }
    }

    List<Box> createBoxList(int index, int length){
        List<Box> list= new ArrayList<Box>();
        for(int i = 0; i < length; i++){
            list.add(new Box(i, index));
        }

        return list;
    }
}