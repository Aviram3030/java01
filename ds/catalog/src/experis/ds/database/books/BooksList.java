package experis.ds.database.books;

import experis.ds.logic.query.Book;
import experis.ds.logic.query.TitleSearchCommands;

import java.util.ArrayList;

public class BooksList{
    private ArrayList<Book> books;

    public BooksList(){
        books = new ArrayList<>();
    }

    public int size() {
        return books.size();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBooks(TitleSearchCommands commands) {
        ArrayList<Book> booksList = new ArrayList<>();

        for(Book book: books){
            String title = book.getTitle();
            if(commands.search(title)){
                booksList.add(book);
            }
        }
        return booksList;
    }

    public Book getBook(int index){
        return books.get(index);
    }
}
