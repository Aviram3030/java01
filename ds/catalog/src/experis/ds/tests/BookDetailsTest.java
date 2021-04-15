package experis.ds.tests;

import experis.ds.database.details.BookDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDetailsTest {

    private BookDetails bookDetails;

    @BeforeEach
    void setUp(){
        String[] details = {"671042858","The Girl Who Loved Tom Gordon","Stephen King","2000","Pocket"};
        try {
              bookDetails = new BookDetails(details);
        } catch (Exception e) {
            System.out.println("Exception worked");
        }
    }

    @Test
    void getIsbn() {
        assertEquals(bookDetails.getIsbn(), "671042858");
    }

    @Test
    void getTitle() {
        assertEquals(bookDetails.getTitle(), "The Girl Who Loved Tom Gordon");
        assertNotEquals(bookDetails.getTitle(), "The Girl Who Loved Gordon");
    }

    @Test
    void getAuthor() {
        assertEquals(bookDetails.getAuthor(), "Stephen King");
    }

    @Test
    void getYear() {
        assertEquals(bookDetails.getYear(), 2000);
    }

    @Test
    void getPublisher() {
        assertEquals(bookDetails.getPublisher(), "Pocket");
    }
}