package experis.ds.database.books;

import java.util.HashMap;
import java.util.function.Function;

public class NamesList<T,K> {

    private HashMap<T, K> authors = new HashMap<>();

    public K get(T authorName){
        return authors.get(authorName);
    }

    public void add(T authorName, Function<T,K> func) {
        K author = authors.get(authorName);

        if(author == null){
            author = func.apply(authorName);
            authors.put(authorName, author);
        }
    }


}
