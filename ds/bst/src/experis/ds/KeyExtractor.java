package experis.ds;

public interface KeyExtractor<K,T>{
    public K extract(T data);
}
