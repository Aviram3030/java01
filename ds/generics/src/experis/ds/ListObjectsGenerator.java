package experis.ds;

import java.util.ArrayList;
import java.util.List;

public class ListObjectsGenerator <T>{
    public static <T> List<T> generator(T start, T stride, int size, IBiFuncObject<T> func){
        List<T> newList = new ArrayList<>();
        newList.add(start);
        for(int i = 0; i < size - 1; i++){
            start = func.apply(start, stride);
            newList.add(start);
        }

        return newList;
    }
}
