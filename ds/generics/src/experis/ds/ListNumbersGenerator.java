package experis.ds;

import java.util.ArrayList;
import java.util.List;

public class ListNumbersGenerator {
    public static List<Number> generator(Number start, Number stride, int size, IBiFuncNumber<Number> func){
        List<Number> newList = new ArrayList<>();
        newList.add(start);
        for(int i = 0; i < size - 1; i++){
            start = func.apply(start, stride);
            newList.add(start);
        }

        return newList;
    }
}
