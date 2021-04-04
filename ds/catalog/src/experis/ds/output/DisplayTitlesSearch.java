package experis.ds.output;

import experis.ds.books.BookDetails;
import java.util.ArrayList;

public class DisplayTitlesSearch {
    public void print(ArrayList<BookDetails> books){
        for(BookDetails book: books){
            System.out.print(book.getIsbn() + "|" + book.getTitle() + "|" + book.getAuthor().getName());
            System.out.println("|" + book.getYear() + "|" + book.getPublisher().getName());
        }
    }
}
