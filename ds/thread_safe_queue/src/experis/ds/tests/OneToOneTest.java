package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OneToOneTest {

    ThreadSafeQueue<Box> queue = new ThreadSafeQueue<>(10);
    final int N = 1000;
    List<Box> products = new ArrayList<>();

    @Test
    void oneToOne() {
        createBoxList();
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

        List<Box> result = consumer.getProducts();
        compareLists(products, result);
        assertEquals(1, queue.size());
    }

    void compareLists(List<Box> products, List<Box> result){
        assertEquals(products.size(), result.size());
        for(int i = 0; i < products.size(); i++){
            assertEquals(products.get(i).getVal(), result.get(i).getVal());
        }
    }

    void createBoxList(){
        for(int i = 0; i < N; i++){
            products.add(new Box(i, 0));
        }
    }
}