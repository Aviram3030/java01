package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ManyToManyTest {
    final int numOfProducers = 100;
    final int numOfConsumers = 10;
    final int N = 2000;

    @Test
    void manyToMany() {
        ThreadSafeQueue<Box> queue = new ThreadSafeQueue<>(100);

        List<List<Box>> products = new ArrayList<>();
        Producer[] producers = new Producer[numOfProducers];
        for(int i = 0; i < numOfConsumers; i++){
            products.add(createBoxList(i, N / (i+1)));
            producers[i] = new Producer(products.get(i), queue);
        }

        ThreadGroup threadGroupProducers = new ThreadGroup(producers);
        ThreadGroup threadGroupConsumers = new ThreadGroup(numOfConsumers,() -> new Consumer(queue));

        threadGroupConsumers.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadGroupProducers.start();

        threadGroupProducers.join();
        queue.enqueue(new Box(-1 , 0));
        threadGroupConsumers.join();

        Consumer[] consumers = new Consumer[numOfConsumers];
        for(int i = 0; i < consumers.length; i++){
            consumers[i] = (Consumer)threadGroupConsumers.get(i);
        }
        List<List<Box>> results = new ArrayList<>(numOfConsumers);
        for(var consumer: consumers){
            results.add(consumer.getProducts());
        }
        assertTrue(checkIfLegal(results));

        Box[][] sortedData = scanTheArray(results, N);
        for(int i = 0; i < products.size(); i++) {
            assertTrue(compareResults(sortedData[i], products.get(i)));
        }
    }

    private Boolean checkIfLegal(List<List<Box>> results){
        int[] min = new int[numOfProducers];
        for(var list: results){
            allToMinusOne(min);
            for(var box: list){
                int index = box.getIndex();
                int val = box.getVal();
                if(min[index] > val){
                    return false;
                }
                min[index] = val;
            }
        }
        return true;
    }

    void allToMinusOne(int[] v){
        Arrays.fill(v, -1);
    }
    Boolean compareResults(Box[] sortedData, List<Box> products) {
        for(int i = 0; i < products.size(); i++){
            if(sortedData[i].getVal() != products.get(i).getVal()){
                return false;
            }
        }
        return true;
    }

    Box[][] scanTheArray(List<List<Box>> results, int n) {
        Box[][] sortedData = new Box[results.size()][n];
        for(var list: results){
            for(var box: list){
                int val = box.getVal();
                int index = box.getIndex();
                sortedData[index][val] = box;
            }
        }
        return sortedData;
    }

    List<Box> createBoxList(int index, int length){
        List<Box> list = new ArrayList<>();
        for(int i = 0; i < length; i++){
            list.add(new Box(i, index));
        }
        return list;
    }

}