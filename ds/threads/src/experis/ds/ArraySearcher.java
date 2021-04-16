package experis.ds;

public class ArraySearcher implements Runnable{
    private final int[] v;
    private final int start;
    private final int end;
    private final int x;
    private static Boolean found = false;

    public ArraySearcher(int[] v, int start, int end, int x){
        this.v = v;
        this.start = start;
        this.end = end;
        this.x = x;
    }

    @Override
    public void run() {
        for (int j = start; j < end; j++) {
            if (v[j] == x || found) {
                found = true;
                return;
            }
        }
    }

    public static Boolean getFound(){
        return found;
    }
}
