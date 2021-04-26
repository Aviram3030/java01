package experis.ds;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class RandomArrayTest {
    final int N = 10_507_007;

    @org.junit.jupiter.api.Test
    void compute() {
        int[] arr = generateRandomArray(N);
        QuickSort qs = new QuickSort(arr,0 , arr.length - 1);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(qs);

        assertTrue(isSorted(arr));
    }


    int[] generateRandomArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(10000);
        }
        return arr;
    }

    public static Boolean isSorted(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i + 1]){
                System.out.println(arr[i]);
                System.out.println(arr[i + 1]);
                return false;
            }
        }
        return true;
    }
}