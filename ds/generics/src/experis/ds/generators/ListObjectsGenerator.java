package experis.ds.generators;

import java.util.ArrayList;
import java.util.List;

public class ListObjectsGenerator <T>{
    public static <T,S,K> List<T> generator(T start, S stride, int size, IBiFuncObject<T,S> func1, IFuncGenerator<S,T> func2){
        List<T> newList = new ArrayList<>();
        newList.add(start);
        for(int i = 0; i < size - 1; i++){
            S val = func1.apply(start, stride);
            start = func2.apply(val);
            newList.add(start);
        }

        return newList;
    }

}
