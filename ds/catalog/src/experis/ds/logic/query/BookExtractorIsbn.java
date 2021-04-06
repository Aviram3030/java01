package experis.ds.logic.query;

import experis.ds.database.books.BookDetails;
import experis.ds.database.fields.ISBN;

import java.util.HashMap;

public class BookExtractorIsbn {
    private HashMap <ISBN, BookDetails> extractor = new HashMap<>();

    public BookDetails getBook(ISBN isbn){
        return extractor.get(isbn);
    }

    public void addBook(BookDetails book, ISBN isbn) {
        extractor.put(isbn, book);
    }
}
