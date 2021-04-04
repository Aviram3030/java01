package experis.ds.userinterface.display;

import experis.ds.database.books.BookDetails;
import java.util.ArrayList;

public class DisplayTitlesSearch {
    public void print(ArrayList<BookDetails> books){
        for(BookDetails book: books){
            System.out.print(book.getIsbn() + "|" + book.getTitle() + "|" + book.getAuthor().getName());
            System.out.println("|" + book.getYear() + "|" + book.getPublisher().getName());
        }
    }
}
