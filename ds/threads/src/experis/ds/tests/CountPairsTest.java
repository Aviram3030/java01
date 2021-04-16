package experis.ds.tests;

import experis.ds.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class countPairsTest {

    @Test
    void countPairs() {
        int[] a = new int[]{1,3,8,4,6};
        Assertions.assertEquals(Main.countPairs(a, 4), 1);
        assertEquals(Main.countPairs(a, 5), 1);
        assertEquals(Main.countPairs(a, 7), 2);
        assertEquals(Main.countPairs(a, 20), 0);

        assertEquals(Main.countPairs(null, 20), 0);

    }
}