package experis.ds.database.fields;

import experis.ds.database.details.Book;


public class ISBN{
    private final String text;
    private Book book = null;

    public ISBN(String text) throws Exception {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Book getBook(){
        return book;
    }

    public void addBook(Book book){
        this.book = book;
    }

}
