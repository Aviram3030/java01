package experis.ds;

import java.util.HashMap;

public class FirstQuestion {
    public static int f1(int[] numbers){
        if(numbers == null){
            return -1;
        }

        HashMap<Integer, Integer> map = new HashMap();
        for(int i = 0; i < numbers.length; i++){
            Integer index = map.get(numbers[i]);
            if(index == null) {
                map.put(numbers[i], i);
            }
            else if(index != -1){
                map.put(numbers[i], -1);
            }
        }

        for(int i = 0; i < numbers.length; i++){
            if(map.get(numbers[i]) == i){
                return numbers[i];
            }
        }

        return -1;
    }
}
