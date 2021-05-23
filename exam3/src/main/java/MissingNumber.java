public class MissingNumber {
    public static int findMissingNumber(int[] v){
        if(v == null || v.length == 0){
            return -1;
        }

        int fullSum = calculateFullSum(v.length + 1);
        int arraySum = calculateSum(v);
        return fullSum - arraySum;
    }

    private static int calculateFullSum(int n){
        return n * (n + 1) / 2;
    }

    private static int calculateSum(int[] v){
        int sum = 0;
        for(var num: v){
            sum += num;
        }
        return sum;
    }
}
