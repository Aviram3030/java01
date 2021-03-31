package experis.ds;

import java.util.HashMap;

public class KeyExtractor<T,K> {
    HashMap<T,K> extractor = new HashMap<>();

    public K extract(T data) {
        return extractor.get(data);
    }

    public void add(T data,K key){
        extractor.put(data, key);
    }

}
