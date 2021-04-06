package experis.ds.logic.query;

import experis.ds.database.books.BookDetails;
import experis.ds.database.fields.Author;

import java.util.ArrayList;
import java.util.HashMap;

public class BookExtractorAuthor {
    private HashMap<Author, ArrayList<BookDetails>> extractor = new HashMap<>();

    public ArrayList<BookDetails> getBooks(Author author){
        return extractor.get(author);
    }

    public void addBook(BookDetails book, Author author){
        ArrayList<BookDetails> bookList = extractor.get(author);
        if(bookList == null){
            bookList = new ArrayList<>();
        }

        bookList.add(book);
        extractor.put(author, bookList);
    }
}
