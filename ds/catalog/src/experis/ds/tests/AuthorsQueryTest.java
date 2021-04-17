package experis.ds.tests;

import experis.ds.database.details.Book;
import experis.ds.database.details.BookDetails;
import experis.ds.database.fields.Author;
import experis.ds.logic.BookCreator;
import experis.ds.logic.query.authorpublisher.AuthorPublisherQuery;
import experis.ds.logic.query.authorpublisher.BiFunc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AuthorsQueryTest {

    private BookDetails bookDetails;
    private Book book;
    private ArrayList<Book> books = new ArrayList<>();
    private BookCreator bookCreator = new BookCreator();

    @BeforeEach
    void setUp() {
        try {
            String[] details = {"671042858","The Girl Who Loved Tom Gordon","Stephen King","2000","Pocket"};
            bookDetails = new BookDetails(details);
            book = bookCreator.create(bookDetails);
            books.add(book);

            String[] secondDetails ={"1552041778","Jane Doe","R. J. Kaiser","1999","Mira Books"};
            bookDetails = new BookDetails(secondDetails);
            book = bookCreator.create(bookDetails);
            books.add(book);

            String[] thirdDetails ={"786004886","The Mentor","R. A. Forster","1998","Pinnacle Books"};
            bookDetails = new BookDetails(thirdDetails);
            book = bookCreator.create(bookDetails);
            books.add(book);

            String[] fourthDetails ={"446674346","Cloud Mountain","Aimee Liu","1998","Warner Books"};
            bookDetails = new BookDetails(fourthDetails);
            book = bookCreator.create(bookDetails);
            books.add(book);


        } catch (Exception e) {
            System.out.println("Exception worked");
            return;
        }
    }

    @Test
    void executeQueryAuthor() {
        BiFunc<Author,Book> biFunc = Book::getAuthor;
        AuthorPublisherQuery<Author> authorsQuery = new AuthorPublisherQuery<>();
        authorsQuery.set(books);
        ArrayList<Author> selectedAuthors = authorsQuery.search("R.J.",biFunc);
        assertEquals(0, selectedAuthors.size());

        selectedAuthors = authorsQuery.search("Kaiser",biFunc);
        assertEquals("R. J. Kaiser", selectedAuthors.get(0).getName());
        assertEquals(1,selectedAuthors.size());

        authorsQuery.clear();
        authorsQuery.set(books);
        selectedAuthors = authorsQuery.search("Aimee",biFunc);
        assertEquals("Aimee Liu", selectedAuthors.get(0).getName());

    }
}