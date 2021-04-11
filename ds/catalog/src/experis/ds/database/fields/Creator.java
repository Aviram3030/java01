package experis.ds.database.fields;

import experis.ds.database.details.Book;

import java.util.ArrayList;

public class Creator {
    private final String name;
    private final ArrayList<Book> books= new ArrayList<>();

    public Creator(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }
}
