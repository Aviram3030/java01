package experis.ds.lmbda;

public interface StatusChecker<T>{
    Boolean apply(T element);
}
