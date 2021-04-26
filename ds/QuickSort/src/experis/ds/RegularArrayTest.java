package experis.ds;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class RegularArrayTest {

    final int N = 1_000_000_000;
    @Test
    void quickSort() {
        int[] arr = makeSequentialArray(N);
        QuickSort qs = new QuickSort(arr,0 , arr.length - 1);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        forkJoinPool.invoke(qs);

        assertTrue(isSorted(arr));
    }

    public static Boolean isSorted(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i + 1]){
                return false;
            }
        }
        return true;
    }

    private static int[] makeSequentialArray(int n) {
        var v = new int[n];
        int j = 0;
        for (int i = v.length - 1; i > 0; i--, j++) {
            v[j] = i;
        }
        return v;
    }
}