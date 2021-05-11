package experis.ds.tests.unlimited;

import static org.junit.jupiter.api.Assertions.*;

import experis.ds.ThreadSafeContainer;
import experis.ds.tests.Box;
import experis.ds.tests.Producer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
class OneToOneTest {

    ThreadSafeContainer<Box> container = new ThreadSafeContainer<>();
    final int N = 1000;
    List<Box> products = new ArrayList<>();

    @Test
    void oneToOne() {
        createBoxList();
        Producer producer = new Producer(products, container);
        Consumer consumer = new Consumer(container);

        Thread produceThread = new Thread(producer);
        Thread consumeThread = new Thread(consumer);

        produceThread.start();
        consumeThread.start();

        try {
            produceThread.join();
            container.put(new Box(-1,0));
            container.put(new Box(-1,0));
            consumeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Box> result = consumer.getProducts();
        compareLists(products, result);

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