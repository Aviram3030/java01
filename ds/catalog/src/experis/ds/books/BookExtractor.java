package experis.ds.books;

import java.util.HashMap;

public class BookExtractor {
    private HashMap<String, BookDetails> extractor = new HashMap<>();

    public BookDetails getBook(String isbn){
        return extractor.get(isbn);
    }

    public void addBook(BookDetails book){
        extractor.put(book.getIsbn(), book);
    }
}
