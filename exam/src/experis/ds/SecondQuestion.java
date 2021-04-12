package experis.ds;

import java.util.HashMap;

public class SecondQuestion <T>{
    public static <T> T f2(T[] obj){
        if(obj == null){
            return null;
        }

        HashMap<T, Integer> map = new HashMap();
        for(int i = 0; i < obj.length; i++){
            Integer index = map.get(obj[i]);
            if(index == null) {
                map.put(obj[i], i);
            }
            else if(index != -1){
                map.put(obj[i], -1);
            }
        }

        for(int i = 0; i < obj.length; i++){
            if(map.get(obj[i]) == i){
                return obj[i];
            }
        }

        return null;
    }
}
