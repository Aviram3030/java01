package experis.ds.tests;

import experis.ds.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class sumTest {

    @Test
    void sum() {
        int[] a = new int[]{1,2,3,4};
        Assertions.assertEquals(Main.sum(a), 10);

        int[] b = new int[]{1,2,3,4,5};
        assertEquals(Main.sum(b), 15);

        int[] c = new int[]{5};
        assertEquals(Main.sum(c), 5);

        assertEquals(Main.sum(null),0);
    }
}