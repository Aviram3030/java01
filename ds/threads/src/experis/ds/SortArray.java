package experis.ds;

public class SortArray implements Runnable{
    private final int start;
    private final int end;
    private final int[] v;

    public SortArray(int[] v, int start, int end){
        this.v = v;
        this.start = start;
        this.end = end;
    }


    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < v.length / 2 - i - 1; j++) {
                if (v[j] > v[j + 1])
                    swap(j + 1, j);
            }
        }
    }

    private void swap(int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
}
