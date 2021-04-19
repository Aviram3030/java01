package experis.ds.tests;

import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FifoTest {

    @Test
    void fifoTest() {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(1000);
        for(int i = 0; i < 1000; i++){
            queue.enqueue(i);

            if(i == 500){
                assertEquals(501, queue.size());
            }
        }

        assertEquals(1000, queue.size());

        for(int i = 0; i < 1000; i++){
            assertEquals(i, queue.dequeue());

            if(i == 500){
                assertEquals(499, queue.size());
            }
        }
    }
}