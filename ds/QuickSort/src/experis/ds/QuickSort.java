package experis.ds;

import java.util.concurrent.RecursiveTask;

public class QuickSort extends RecursiveTask<Void> {
    private static final int SEQUENTIAL_THRESHOLD = 1024;
    private int[] arr;
    private int start;
    private int end;

    public QuickSort(int[] arr,int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public void sort(int arr[], int low, int high)
    {
        if (low >= high) {
            return;
        }
        int pivot = partition(arr, low, high);
        sort(arr, low, pivot - 1);
        sort(arr, pivot + 1, high);
    }

    private int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++)
        {
            if (arr[j] <= pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int v[], int i, int j){
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    @Override
    protected Void compute() {
        if(end - start <= SEQUENTIAL_THRESHOLD) {
            sort(arr, start, end);
        }
        else {
            int mid = start + (end - start) / 2;
            var left = new QuickSort(arr, start, mid);
            var right = new QuickSort(arr, mid, end);
            invokeAll(left, right);
        }
        return null;
    }
}
