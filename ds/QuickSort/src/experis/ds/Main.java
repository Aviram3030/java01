package experis.ds;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args){
        int[] arr = makeSequentialArray(100_000);
        QuickSort qs = new QuickSort(arr,0 , arr.length - 1);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        forkJoinPool.execute(qs);

        System.out.println(isSorted(arr));
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
        for (int i = 0; i < v.length; i++) {
            v[i] = i;
        }
        return v;
    }
}
