package experis.ds;

import java.util.concurrent.RecursiveTask;

public class QuickSort extends RecursiveTask<Void> {
    private static final int SEQUENTIAL_THRESHOLD = 1024;
    private int[] arr;
    private int start;
    private int end;

    public QuickSort(int[] arr ,int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Void compute() {
        if(end - start <= SEQUENTIAL_THRESHOLD) {
            quickSort(start, end);
        }
        else {
            int pivotIndex = partition(start, end);
            var left = new QuickSort(arr, start, pivotIndex - 1);
            var right = new QuickSort(arr, pivotIndex, end);
            invokeAll(left, right);
        }
        return null;
    }

    private void quickSort(int left, int right) {
        int pivotIndex = partition(left, right);
        if (left < pivotIndex - 1) {
            quickSort(left, pivotIndex - 1);
        }
        if (pivotIndex < right) {
            quickSort(pivotIndex, right);
        }
    }

    private int partition(int left, int right)
    {
        int mid = (left + right) / 2;
        int pivot = arr[mid];
        while (left <= right){
            while (arr[right] > pivot){
                right--;
            }
            while (arr[left] < pivot){
                left++;
            }
            if(left > right){
                break;
            }
            swap(left,right);
            left++;
            right--;
        }
        return left;
    }

    private void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
