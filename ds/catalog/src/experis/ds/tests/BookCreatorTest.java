package experis.ds.tests;

import experis.ds.database.details.Book;
import experis.ds.database.details.BookDetails;
import experis.ds.logic.BookCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookCreatorTest {

    private BookDetails bookDetails;
    private Book book;

    @BeforeEach
    void setUp() throws Exception {
        String[] details = {"671042858","The Girl Who Loved Tom Gordon","Stephen King","2000","Pocket"};
        try {
            bookDetails = new BookDetails(details);
        } catch (Exception e) {
            System.out.println("Exception worked");
        }
        BookCreator bookCreator = new BookCreator();
        book = bookCreator.create(bookDetails);
    }

    @Test
    void getIsbn() {
        assertEquals(book.getIsbn().getText(), "671042858");
    }

    @Test
    void getTitle() {
        assertEquals(book.getTitle(), "The Girl Who Loved Tom Gordon");
        assertNotEquals(book.getTitle(), "The Girl Who Loved Gordon");
    }

    @Test
    void getAuthor() {
        assertEquals(book.getAuthor().getName(), "Stephen King");
    }

    @Test
    void getYear() {
        assertEquals(book.getYear(), 2000);
    }

    @Test
    void getPublisher() {
        assertEquals(book.getPublisher().getName(), "Pocket");
    }
}