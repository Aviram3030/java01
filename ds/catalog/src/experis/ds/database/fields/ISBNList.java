package experis.ds.database.fields;

import java.util.HashMap;

public class ISBNList {
    private HashMap<String,ISBN> isbnKeys= new HashMap<>();

    public ISBN get(String text){
        return isbnKeys.get(text);
    }

    public void add(ISBN isbn){
        isbnKeys.put(isbn.getText(), isbn);
    }
}
