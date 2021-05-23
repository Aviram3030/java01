public class Fibonacci {
    public static int calculate(int n){
        if(n < 0){
            return -1;
        }
        if(n == 0){
            return 0;
        }

        int[] v = new int[n + 1];
        return dynamicCalculation(n, v);
    }

    private static int dynamicCalculation(int n, int[] v){
        if(v[n] != 0){
            return v[n];
        }

        if(n <= 2){
            return 1;
        }

        v[n] = dynamicCalculation(n - 1, v) + dynamicCalculation(n - 2, v);
        return v[n];
    }
}
