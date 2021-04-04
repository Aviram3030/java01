package experis.ds.database.persons.books;

import experis.ds.commands.TitleSearchCommands;

import java.util.ArrayList;

public interface IBookList {

    void addBook(BookDetails book);

    ArrayList<BookDetails> searchBooks(TitleSearchCommands commands);
}
