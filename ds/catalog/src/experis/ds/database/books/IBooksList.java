package experis.ds.database.books;

import experis.ds.logic.query.TitleSearchCommands;

import java.util.ArrayList;

public interface IBooksList {

    int size();

    void addBook(BookDetails book);

    ArrayList<BookDetails> getBooks(TitleSearchCommands commands);

    BookDetails getBook(int index);
}
