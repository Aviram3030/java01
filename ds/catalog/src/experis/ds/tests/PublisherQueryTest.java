package experis.ds.tests;

import experis.ds.database.details.Book;
import experis.ds.database.details.BookDetails;
import experis.ds.database.fields.Publisher;
import experis.ds.logic.BookCreator;
import experis.ds.logic.query.authorpublisher.AuthorPublisherQuery;
import experis.ds.logic.query.authorpublisher.BiFunc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PublisherQueryTest {
    private final ArrayList<Book> books = new ArrayList<>();
    private final BookCreator bookCreator = new BookCreator();

    @BeforeEach
    void setUp() {
        try {
            BookDetails bookDetails;
            Book book;
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
        }
    }

    @Test
    void executeQueryPublisher() {
        BiFunc<Publisher, Book> biFunc = Book::getPublisher;
        AuthorPublisherQuery<Publisher> publishersQuery = new AuthorPublisherQuery<>();
        publishersQuery.set(books);

        ArrayList<Publisher> selectedPublisher = publishersQuery.search("Books",biFunc);
        assertEquals(3, selectedPublisher.size());
        assertEquals("Pinnacle Books", selectedPublisher.get(0).getName());
        assertEquals("Mira Books", selectedPublisher.get(1).getName());
        assertEquals("Warner Books", selectedPublisher.get(2).getName());
    }
}