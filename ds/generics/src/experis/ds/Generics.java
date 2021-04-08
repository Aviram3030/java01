package experis.ds;

import java.util.ArrayList;
import java.util.Collections;
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
        if(elements == null || elements.size() == 0){
            return null;
        }

        IFunc<T> a = (x,y) -> x.compareTo(y) <= 0;
        return findTheMost(elements, a);
    }

    public static <T extends Comparable<T>> T min(List<T> elements) {
        if(elements == null || elements.size() == 0){
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

    private static <T extends Comparable<T>> T findTheMost(List<T> elements, IFunc<T> func) {
        T val = elements.get(0);
        for(int i = 1; i < elements.size(); i++){
            T element = elements.get(i);
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
        T min = min(elements);
        elements.remove(min);

        return min;
    }

    //time complexity: O(1.5 * N)
    public static <T extends Comparable<T>> List<T> maxAndMin(List<T> elements){
        if(elements == null || elements.size() == 0){
            return null;
        }

        List<T> maxMin = new ArrayList<>();
        if(elements.size() == 1){
            maxMin.add(elements.get(0));
            maxMin.add(elements.get(0));
            return maxMin;
        }

        T max;
        T min;

        T a = elements.get(0);
        T b = elements.get(1);

        if(a.compareTo(b) > 0){
            max = a;
            min = b;
        }
        else{
            max = b;
            min = a;
        }

        int i = 2;
        for(;i < elements.size() - 1; i = i + 2){
            a = elements.get(i);
            b = elements.get(i + 1);

            if(a.compareTo(b) > 0){
                max = getBigger(a,max);
                min = getSmaller(b,min);

            }
            else{
                max = getBigger(b,max);
                min = getSmaller(a,min);
            }
        }

        if(elements.size() % 2 == 1){
            a = elements.get(i - 1);
            max = getBigger(a, max);
            min = getSmaller(b, min);
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
