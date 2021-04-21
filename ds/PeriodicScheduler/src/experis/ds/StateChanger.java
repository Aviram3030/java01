package experis.ds;

public interface StateChanger<T>{
    void apply(T element);
}
