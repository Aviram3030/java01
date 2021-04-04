package experis.ds.database.creators;

import java.util.HashMap;

public class ISBNList {
    private HashMap<String,ISBN> keys= new HashMap<>();

    public ISBN get(String text){
        return keys.get(text);
    }

    public void add(ISBN isbn){
        keys.put(isbn.getText(), isbn);
    }
}
