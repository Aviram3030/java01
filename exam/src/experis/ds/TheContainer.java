package experis.ds;

import java.util.HashMap;
import java.util.Stack;

public class TheContainer <T extends Comparable<T>>{
    private final Stack<T> stack = new Stack<>();
    private final HashMap<T, Integer> map = new HashMap<>(); // counter for each element
    private final HashMap<T, T> previousMin = new HashMap<>(); // helps to find the previous min
    private T min = null;

    public void push(T a){
        Integer objectCounter = map.get(a);

        if(objectCounter == null || objectCounter == 0){
            if(stack.isEmpty() || min.compareTo(a) > 0){
                previousMin.put(a, min);
                min = a;
            }
            stack.push(a);
            map.put(a, 1);
            return;
        }

        map.put(a, ++objectCounter);
        stack.push(a);
    }

    public T pop(){
        if(stack.isEmpty()){
            return null;
        }
        T a = stack.pop();

        int objectCounter = map.get(a);
        if(objectCounter == 1 && min == a){
            min = previousMin.get(a);
        }

        map.put(a, --objectCounter);
        return a;
    }

    public T min() {
        return min;
    }

    public void forEach(Func<T> func){
        Stack<T> temp = new Stack<>();
        int size = stack.size();

        for(int i = 0; i < size; i++){
            T a = stack.pop();
            func.apply(a);
            temp.push(a);
        }

        for(int i = 0; i < size; i++){
            T a = temp.pop();
            stack.push(a);
        }
    }

}
