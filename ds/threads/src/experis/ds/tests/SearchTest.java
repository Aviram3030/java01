package experis.ds.tests;

import experis.ds.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @Test
    void search() {
        int[] v = new int[]{8,5,3,56,1,9,42};
        assertTrue(Main.search(v,5, 1));
        assertTrue(Main.search(v,5, 5));
        assertTrue(Main.search(v,5, 7));
        assertTrue(Main.search(v,5, 10));

        assertFalse(Main.search(v,777, 1));
        assertFalse(Main.search(v,777, 5));
        assertFalse(Main.search(v,777, 7));
        assertFalse(Main.search(v,777, 10));

        assertFalse(Main.search(null,42, 1));
    }
}