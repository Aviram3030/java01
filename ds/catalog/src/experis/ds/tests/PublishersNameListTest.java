package experis.ds.tests;

import experis.ds.database.creators.Publisher;
import experis.ds.database.creators.PublishersNameList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublishersNameListTest {

    @Test
    void add() {
        PublishersNameList publishers = new PublishersNameList();
        Publisher a = publishers.add("Publisher a");
        Publisher b = publishers.add("Publisher a");
        assertTrue(a == b);
        b = publishers.add("Author b");
        assertFalse(a == b);
    }
}