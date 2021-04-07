package experis.ds.logic.display;

import experis.ds.database.books.BookDetails;
import experis.ds.logic.query.Book;

import java.util.ArrayList;

public class DisplayBooksList {
    public void print(ArrayList<Book> books){
        for(Book book: books){
            System.out.print(book.getIsbn().getText() + "|" + book.getTitle() + "|" + book.getAuthor().getName());
            System.out.println("|" + book.getYear() + "|" + book.getPublisher().getName());
        }
    }
}
