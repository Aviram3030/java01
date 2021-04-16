package experis.ds;

class Pair {
    private final Integer firstLoopResult;
    private final Integer secondLoopResult;

    public Pair(Integer firstLoopResult, Integer secondLoopResult) {
        this.firstLoopResult = firstLoopResult;
        this.secondLoopResult = secondLoopResult;
    }

    public Integer getFirstLoopResult() {
        return firstLoopResult;
    }

    public Integer getSecondLoopResult() {
        return secondLoopResult;
    }
}

public class PairsSum implements Runnable {
    private final int[] v;
    private final int start;
    private final int sum;
    private Integer firstLoop;
    private Integer secondLoop;

    public PairsSum(int[] v, int start, int sum) {
        this.v = v;
        this.start = start;
        this.sum = sum;
    }

    @Override
    public void run() {
        for (int j = start + 1; j < v.length; j++) {
            if (v[start] + v[j] == sum) {
                firstLoop = v[start];
                break;
            }
        }

        if (start + 1 == v.length - start - 1) {
            return;
        }

        for (int k = v.length - start - 1; k < v.length - 1; k++) {
            if (v[v.length - start] + v[k] == sum) {
                secondLoop = v[v.length - start];
                return;
            }
        }
    }

    public Pair getResult() {
        return new Pair(firstLoop, secondLoop);
    }
}