package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

class ManyToManyTest {
    @Test
    void manyToMany() {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(new Integer[1000]);
        Integer[] products = new Integer[1000];

        for (int i = 0; i < products.length; i++) {
            products[i] = i;
        }

        final int numOfProducers = 100;
        final int numOfConsumers = 10;

        Producer<Integer>[] producers = makeProducers(queue, numOfProducers, products);
        Consumer<Integer>[] consumers = makeConsumers(queue, numOfConsumers);

        Thread[] threads = new Thread[numOfProducers + numOfConsumers];

        int i = 0;
        int j = 0;
        int k = 0;
        while(k < threads.length){
            if(i < producers.length){
                threads[k] = new Thread(producers[i]);
                i++;
                k++;
            }
            if(j < consumers.length){
                threads[j] = new Thread(consumers[j]);
                j++;
                k++;
            }
        }

        //TODO
        int sum = 0;
        for(int t = 0; t < consumers.length; t++){
             consumers[i].getProducts();
        }
    }

    Producer[] makeProducers(ThreadSafeQueue<Integer> queue, int numOfProducers, Integer[] products){
        Producer[] producers = new Producer[numOfProducers];
        int start = 0;
        int interval = 1000 / numOfProducers;
        int end = interval;

        for(int i = 0; i < numOfProducers; i++){
            for(int j = 0; j < end; j++){
                Integer[] arr = makeArray(start , end);
                producers[i] = new Producer(arr, queue);
            }
            start = end;
            end += interval;
        }
        return producers;
    }

    Integer[] makeArray(int start, int end){
        Integer[] arr = new Integer[end - start];
        for(int i = start; i < end; i++){
            arr[start] = start;
        }

        return arr;
    }

    Consumer[] makeConsumers(ThreadSafeQueue<Integer> queue, int numOfConsumers){
        Consumer[] consumers = new Consumer[numOfConsumers];
        int start = 0;
        int interval = 1000 / numOfConsumers;
        int end = interval;

        for(int i = 0; i < numOfConsumers; i++){
            for(int j = 0; j < end; j++){
                consumers[i] = new Consumer(new Integer[interval], queue);
            }
        }
        return consumers;
    }

    void startThreads(Thread[] threads){
        for(Thread thread: threads){
            thread.start();
        }
    }

    void joinThreads(Thread[] threads){
        for(Thread thread: threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}