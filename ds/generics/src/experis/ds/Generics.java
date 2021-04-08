package experis.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Generics{

    public static Double average(List<? extends Number> elements){
        if(elements == null){
            return null;
        }

        double sum = elements.get(0).doubleValue();
        for(int i = 1; i < elements.size(); i++){
            sum += elements.get(i).doubleValue();
        }

        sum /= elements.size();
        return sum;
    }

    public static <T extends Comparable<T>> T max(List<T> elements) {
        if(elements == null || elements.isEmpty()){
            return null;
        }

        IFunc<T> a = (x,y) -> x.compareTo(y) <= 0;
        return findTheMost(elements.iterator(), a);
    }

    public static <T extends Comparable<T>> T min(Iterator<T> elements) {
        if(elements == null || !elements.hasNext()){
            return null;
        }

        IFunc<T> a = (x,y) -> x.compareTo(y) > 0;
        return findTheMost(elements, a);
    }

    public static <T extends Comparable<T>> void bubbleSort(List<T> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            for (int j = 0; j < elements.size() - i - 1; j++) {
                if (elements.get(j).compareTo(elements.get(j + 1)) > 0) {
                    Collections.swap(elements, j, j + 1);
                }
            }
        }
    }

    private static <T extends Comparable<T>> T findTheMost(Iterator<T> elements, IFunc<T> func) {
        T val = elements.next();
        while(elements.hasNext())
        {
            T element = elements.next();
            if (func.apply(val, element)) {
                val = element;
            }
        }

        return val;
    }


    public static <T> T mid(List<T> elements) {
        if(elements == null || elements.size() == 0){
            return null;
        }

        return elements.get(elements.size() / 2);
    }

    public static <T extends Comparable<T>> T removeMin(List<T> elements){
        T min = min(elements.iterator());
        elements.remove(min);

        return min;
    }

    //time complexity: O(1.5 * N)
    public static <T extends Comparable<T>> List<T> maxAndMin(Iterator<T> elements){
        if(elements == null || !elements.hasNext()){
            return null;
        }

        List<T> maxMin = new ArrayList<>();

        T max;
        T min;

        T a = elements.next();
        if(!elements.hasNext()){
            maxMin.add(a);
            maxMin.add(a);
            return maxMin;
        }
        T b = elements.next();


        if(a.compareTo(b) > 0){
            max = a;
            min = b;
        }
        else{
            max = b;
            min = a;
        }

        while(elements.hasNext()){
            a = elements.next();
            if(!elements.hasNext()){
                max = getBigger(a, max);
                min = getSmaller(a, min);

                maxMin.add(max);
                maxMin.add(min);
                return maxMin;
            }
            b = elements.next();

            if(a.compareTo(b) > 0){
                max = getBigger(a,max);
                min = getSmaller(b,min);

            }
            else{
                max = getBigger(b,max);
                min = getSmaller(a,min);
            }
        }

        maxMin.add(max);
        maxMin.add(min);
        return maxMin;
    }

    private static <T extends Comparable<T>> T getBigger(T a, T b){
        if(a.compareTo(b) > 0){
            return a;
        }
        return b;
    }

    private static <T extends Comparable<T>> T getSmaller(T a, T b){
        if(a.compareTo(b) > 0){
            return b;
        }
        return a;
    }


}
