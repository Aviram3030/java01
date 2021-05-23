import java.util.HashMap;
import java.util.HashSet;

public class MultiValuedMap<T,S>{
    private final HashMap<T, HashSet<S>> obj = new HashMap<>();

    public void put(T key, S val){
        HashSet<S> values = obj.get(key);
        if(values == null){
            values = new HashSet<>();
            obj.put(key, values);
        }
        values.add(val);
    }

    public HashSet<S> get(T key){
        return obj.get(key);
    }
}
