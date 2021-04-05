package experis.ds.database.books;

import experis.ds.logic.query.TitleSearchCommands;

import java.util.ArrayList;

public interface IBooksList {

    void addBook(BookDetails book);

    ArrayList<BookDetails> getBooks(TitleSearchCommands commands);
}
