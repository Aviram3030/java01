package experis.ds;

public class WaterWeightCalculator {

    //Time: O(n)
    public static int getWeight(int[] v){
        if(v.length < 2){
            return 0;
        }

        int sum = 0;
        int start = 0;
        for(int i = 1; i < v.length; i++){
            if(v[start] < v[i]){
                sum += measureFromLeft(v, start, i);
                start = i;
            }
        }

        if(start == 0){
            return 0;
        }
        else if(start < v.length - 2){
            sum += measureTheRest(v, start);
        }
        return sum;
    }

    private static int measureTheRest(int[] v, int start) {
        int end = v.length - 1;
        int sum = 0;
        for(int i = v.length - 2; i >= start; i--){
            if(v[end] < v[i]) {
                sum += measureFromRight(v, i, end);
                end = i;
            }
        }
        return sum;
    }

    private static int measureFromRight(int[] v, int start, int end) {
        int sum = 0;
        for(int i = end; i > start; i--){
            sum += v[end] - v[i];
        }

        return sum;
    }


    private static int measureFromLeft(int[] v, int start, int end) {
        int sum = 0;
        for(int i = start; i < end; i++){
            sum += v[start] - v[i];
        }

        return sum;
    }
}