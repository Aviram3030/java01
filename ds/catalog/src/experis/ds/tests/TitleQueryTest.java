package experis.ds.tests;

import experis.ds.database.details.Book;
import experis.ds.database.details.BookDetails;
import experis.ds.logic.BookCreator;
import experis.ds.logic.query.title.TitleQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TitleQueryTest {
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
    void firstTest() {
        TitleQuery titleQuery = new TitleQuery();
        String[] titles = new String[]{"The","girl"};

        titleQuery.setBooks(books);
        titleQuery.load(titles);
        ArrayList<Book> books = titleQuery.getBooksByTitle();

        assertEquals(1, books.size());
        assertEquals("The Girl Who Loved Tom Gordon", books.get(0).getTitle());
    }

    @Test
    void secondTest() {
        TitleQuery titleQuery = new TitleQuery();
        String[] titles = new String[]{"The","girl","-who"};

        titleQuery.setBooks(books);
        titleQuery.load(titles);
        ArrayList<Book> books = titleQuery.getBooksByTitle();

        assertEquals(0, books.size());
    }

    @Test
    void thirdTest() {
        TitleQuery titleQuery = new TitleQuery();
        String[] titles = new String[]{"The","-who"};

        titleQuery.setBooks(books);
        titleQuery.load(titles);
        ArrayList<Book> books = titleQuery.getBooksByTitle();

        assertEquals(1, books.size());
        assertEquals("The Mentor", books.get(0).getTitle());
    }
}