package experis.ds.tests;

import experis.ds.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {

    @Test
    void firstTest() {
        int[] a = new int[]{1,2,3,4};
        Assertions.assertEquals(Main.sum(a), 10);

        assertEquals(Main.sum(null),0);
    }

    @Test
    void secondTest(){
        int[] a = new int[]{1,2,3,4,5};
        assertEquals(Main.sum(a), 15);
    }

    @Test
    void thirdTest(){
        int[] a = new int[]{5};
        assertEquals(Main.sum(a), 5);
    }
}