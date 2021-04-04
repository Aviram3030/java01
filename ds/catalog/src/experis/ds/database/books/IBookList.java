package experis.ds.database.books;

import experis.ds.logic.Query.TitleSearchCommands;

import java.util.ArrayList;

public interface IBookList {

    void addBook(BookDetails book);

    ArrayList<BookDetails> searchBooks(TitleSearchCommands commands);
}
