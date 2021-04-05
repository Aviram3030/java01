package experis.ds.database.books;

import experis.ds.logic.query.TitleSearchCommands;

import java.util.ArrayList;

public class BooksList implements IBooksList {
    private ArrayList<BookDetails> books;

    public BooksList(){
        books = new ArrayList<>();
    }

    @Override
    public void addBook(BookDetails book) {
        books.add(book);
    }

    @Override
    public ArrayList<BookDetails> getBooks(TitleSearchCommands commands) {
        ArrayList<BookDetails> booksList = new ArrayList<>();

        for(BookDetails book: books){
            String title = book.getTitle();
            if(commands.search(title)){
                booksList.add(book);
            }
        }
        return booksList;
    }
}
