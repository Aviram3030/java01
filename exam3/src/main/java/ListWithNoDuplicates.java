import java.util.HashSet;
import java.util.LinkedList;

public class ListWithNoDuplicates {
    public static <T> void deleteDuplicates(LinkedList<T> list){
        if(list == null){
            return;
        }
        HashSet<T> noDuplicates = new HashSet<>();
        int i = 0;
        while(i < list.size()){
            T element = list.get(i);
            if(noDuplicates.contains(element)){
                list.remove(element);
            }
            else{
                noDuplicates.add(element);
                i++;
            }
        }
    }
}
