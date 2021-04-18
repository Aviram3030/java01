package experis.ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoConsumersToOneProducer {

    @Test
    void twoConsumersToOneProducer() {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(new Integer[2000]);
        Integer[] products = new Integer[2000];

        for (int i = 0; i < products.length; i++) {
            products[i] = i;
        }

        Consumer<Integer> firstConsumer = new Consumer<>(new Integer[1000], queue);
        Consumer<Integer> secondConsumer = new Consumer<>(new Integer[1000], queue);
        Producer<Integer> producer = new Producer<>(products, queue);

        Thread firstConsumerThread = new Thread(firstConsumer);
        Thread secondConsumerThread = new Thread(secondConsumer);
        Thread producerThread = new Thread(producer);

        firstConsumerThread.start();
        secondConsumerThread.start();
        producerThread.start();


        try {
            firstConsumerThread.join();
            secondConsumerThread.join();
            producerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(checkArrays(firstConsumer.getProducts(), secondConsumer.getProducts()));
    }

    private Boolean checkArrays(Integer[] firstProducts, Integer[] secondProducts) {
        for(int i = 0; i < firstProducts.length; i++){
            if(firstProducts[i] > 2 * i && secondProducts[i] > 2 * i){
                System.out.println(i +" "+ firstProducts[i]);
                return false;
            }
        }
        return true;
    }
}