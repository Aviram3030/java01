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
                sum += calculateWeightBetween(v, start, i);
                start = i;
            }
        }

        if(start == 0){
            return 0;
        }
        else if(start != v.length - 1){
            sum += calculateTheRest(v, start);
        }
        return sum;
    }

    private static int calculateTheRest(int[] v, int start) {
        int end = findTheMaxFromTheEnd(v, start);
        int sum = 0;
        for(int i = end - 1; i > start; i--){
            sum += v[end] - v[i];
        }

        return sum;
    }

    private static int findTheMaxFromTheEnd(int[] v, int start) {
        int maxIndex = start + 1;
        for(int i = v.length - 1; i > start + 1; i--){
            if(v[i] > v[maxIndex]){
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private static int calculateWeightBetween(int[] v, int start, int end) {
        int sum = 0;
        for(int i = start; i < end; i++){
            sum += v[start] - v[i];
        }

        return sum;
    }
}
