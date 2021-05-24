import java.util.HashMap;
import java.util.LinkedList;

public class ListWithNoAllDuplicates {
    public static <T> void deleteAllDuplicates(LinkedList<T> list){
        if(list == null){
            return;
        }

        HashMap<T, Boolean> noDuplicates = new HashMap<>();
        int i = 0;
        while(i < list.size()){
            T element = list.get(i);
            if(noDuplicates.containsKey(element)){
                if(!noDuplicates.get(element)) {
                    noDuplicates.put(element, true);
                    list.remove(element);
                }
                list.remove(element);
            }
            else{
                noDuplicates.put(element, false);
                i++;
            }
        }
    }
}
