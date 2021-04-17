package experis.ds.tests;

import experis.ds.database.details.Book;
import experis.ds.database.details.BookDetails;
import experis.ds.database.fields.ISBN;
import experis.ds.logic.BookCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ISBNTest {

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

            String[] fourthDetails ={"4466746","Cloud Mountain","Aimee Liu","1998","Warner Books"};
            bookDetails = new BookDetails(fourthDetails);
            book = bookCreator.create(bookDetails);
            books.add(book);


        } catch (Exception e) {
            System.out.println("Exception worked");
        }
    }

    @Test
    void testingISBN(){
        HashMap<String, ISBN> isbnElements = bookCreator.getIsbnElements();
        assertEquals("671042858", isbnElements.get("671042858").getText());
        assertEquals("1552041778", isbnElements.get("1552041778").getText());
        assertNull(isbnElements.get("4466746"));
    }
}