package experis.ds.database.books;

import experis.ds.database.creators.ISBN;

import java.util.HashMap;

public class BookExtractorIsbn {
    private HashMap <ISBN, BookDetails> extractor = new HashMap<>();

    public BookDetails getBook(ISBN isbn){
        return extractor.get(isbn);
    }

    public void addBook(BookDetails book){
        extractor.put(book.getIsbn(), book);
    }
}
