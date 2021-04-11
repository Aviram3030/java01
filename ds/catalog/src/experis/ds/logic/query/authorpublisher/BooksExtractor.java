package experis.ds.logic.query.authorpublisher;


import experis.ds.database.fields.Creator;
import experis.ds.database.details.Book;

import java.util.ArrayList;

public class BooksExtractor {
    public static ArrayList<Book> extractBooks(ArrayList<? extends Creator> creators){
        ArrayList<Book> books = new ArrayList<>();
        for(Creator author: creators){
            ArrayList<Book> authorBooks = author.getBooks();
            books.addAll(authorBooks);
        }

        return books;
    }
}
