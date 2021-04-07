package experis.ds.database.fields;

import java.util.HashMap;
import java.util.function.Function;

public class NamesList<T,K> {

    private HashMap<T, K> elements = new HashMap<>();

    public K get(T elementName){
        return elements.get(elementName);
    }

    public void add(T elementName, Function<T,K> func) {
        K element = elements.get(elementName);

        if(element == null){
            element = func.apply(elementName);
            elements.put(elementName, element);
        }
    }


}
