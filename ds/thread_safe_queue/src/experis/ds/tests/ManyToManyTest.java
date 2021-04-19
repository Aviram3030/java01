package experis.ds.tests;

import experis.ds.Consumer;
import experis.ds.Producer;
import experis.ds.ThreadSafeQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ManyToManyTest {
    @Test
    void manyToMany() {
        ThreadSafeQueue<Double> queue = new ThreadSafeQueue<>(100);
        Double[] products = new Double[1000];

        for (int i = 0; i < products.length; i++) {
            products[i] = (double)i;
        }

        final int numOfProducers = 100;
        final int numOfConsumers = 10;

        ThreadGroup threadGroupProducers = new ThreadGroup(numOfProducers,() -> new Producer<>(products, queue));
        ThreadGroup threadGroupConsumers = new ThreadGroup(numOfConsumers,() -> new Consumer<>(new Double[10000], queue));

        threadGroupConsumers.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadGroupProducers.start();

        threadGroupConsumers.join();
        threadGroupProducers.join();

        Consumer<Double>[] consumers = new Consumer[numOfConsumers];
        Double[][] results = new Double[numOfConsumers][1000];

        double mul = (double)numOfConsumers / (double)numOfProducers;
        for(int i = 0; i < numOfConsumers; i++){
            consumers[i] = (Consumer<Double>)threadGroupConsumers.get(i);
            results[i] = consumers[i].getProducts();
        }

        checkArrays(results, mul);

    }

    private Boolean checkCondition(Double product,int index , double mul){
        if(product > index * mul){
            return false;
        }
        return true;
    }

    private Boolean checkArrays(Double[][] products, double mul){
        for(int i = 0; i < products[0].length; i++){
            Boolean check = false;
            for(int j = 0; j < products.length; j++){
                if(checkCondition(products[j][i], i, mul)){
                    check = true;
                    break;
                }
            }
            if(!check){
                return false;
            }
        }
        return true;
    }


    Integer[] makeArray(int start, int end){
        Integer[] arr = new Integer[end - start];
        for(int i = start; i < end; i++){
            arr[start] = start;
        }

        return arr;
    }
}