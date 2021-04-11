package experis.ds.generators;

import java.util.ArrayList;
import java.util.List;

public class ListNumbersGenerator {
    public static List<Double> generator(Double start, Number stride, int size, IBiFuncNumber<Double,Number> func){
        List<Double> newList = new ArrayList<>();
        newList.add(start);
        for(int i = 0; i < size - 1; i++){
            start = func.apply(start, stride);
            newList.add(start);
        }

        return newList;
    }
}
