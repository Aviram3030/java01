package experis.ds.database.fields;

import experis.ds.logic.query.Book;

public class ISBN {
    private String text;
    private Book book;

    public ISBN(String text) throws Exception {
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
