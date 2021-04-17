package experis.ds.logic.query.authorpublisher;


import experis.ds.database.details.Book;
import experis.ds.database.fields.Creator;

import java.util.ArrayList;
import java.util.HashMap;

public class AuthorPublisherQuery <T extends Creator>{
    private ArrayList<Book> books = null;
    private final HashMap<String, T> details = new HashMap<>();

    public void set(ArrayList<Book> books){
        this.books = books;
    }

    public ArrayList<T> search(String text, BiFunc<T,Book> biFunc){
        for (Book book : books) {
            T obj = biFunc.apply(book);
            String name = obj.getName();
            String[] splitNames = name.split(" ");
            if (isContain(splitNames, text.toLowerCase()) && details.get(name) == null) {
                details.put(name, obj);
            }
        }

        return convertToArray();
    }

    private ArrayList<T> convertToArray(){
        ArrayList<T> obj = new ArrayList<>();
        for (String key: details.keySet()) {
            obj.add(details.get(key));
        }

        return obj;
    }

    private boolean isContain(String[] source, String sub){
        for(String splitName: source){
            if(splitName.equals("")){
                continue;
            }
            if(splitName.toLowerCase().equals(sub)){
                return true;
            }
        }

        return false;
    }

    public void clear(){
        details.clear();
        books = null;
    }

}
