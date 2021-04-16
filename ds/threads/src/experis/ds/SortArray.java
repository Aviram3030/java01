package experis.ds;

public class SortArray implements Runnable{
    private final int[] v;
    private final int start;
    private final int end;

    public SortArray(int[] v, int start, int end){
        this.v = v;
        this.start = start;
        this.end = end;
    }


    @Override
    public void run() {
        for (int i = start, k = 0; i < end; i++,k++) {
            for (int j = start; j < end - k - 1; j++) {
                if (v[j] > v[j + 1]) {
                    swap(j + 1, j);
                }
            }
        }
    }

    private void swap(int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
}
