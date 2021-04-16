package experis.ds;

public class SumTask implements Runnable {
    private final int[] v;
    private int sum = 0;
    private final int start;
    private final int end;

    public SumTask(int[] v, int start, int end) {
        this.v = v;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += v[i];
        }
    }

    public int getSum() {
        return sum;
    }
}