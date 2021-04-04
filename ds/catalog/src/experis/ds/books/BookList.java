package experis.ds.books;

import experis.ds.commands.TitleSearchCommands;

import java.util.ArrayList;

public class BookList implements IBookList{
    private ArrayList<BookDetails> books;

    public BookList(){
        books = new ArrayList<>();
    }

    @Override
    public void addBook(BookDetails book) {
        books.add(book);
    }

    @Override
    public ArrayList<BookDetails> searchBooks(TitleSearchCommands commands) {
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
