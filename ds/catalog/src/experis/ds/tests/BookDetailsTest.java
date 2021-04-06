package experis.ds.tests;

import experis.ds.database.books.BookDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDetailsTest {

    private BookDetails book;

    @BeforeEach
    void setUp(){
        String[] details = {"671042858","The Girl Who Loved Tom Gordon","Stephen King","2000","Pocket"};
        try {
              book = new BookDetails(details);
        } catch (Exception e) {
            System.out.println("Exception worked");
        }
    }

    @Test
    void getIsbn() {
        assertTrue(book.getIsbn().equals("0671042858"));
        assertFalse(book.getIsbn().equals("671042858"));
    }

    @Test
    void getTitle() {
        assertTrue(book.getTitle().equals("The Girl Who Loved Tom Gordon"));
        assertFalse(book.getTitle().equals("The Girl Who Loved Gordon"));
    }

    @Test
    void getAuthor() {
        assertTrue(book.getAuthor().equals("Stephen King"));
    }

    @Test
    void getYear() {
        assertTrue(book.getYear().equals("2000"));
    }

    @Test
    void getPublisher() {
        assertTrue(book.getPublisher().equals("Pocket"));
    }
}