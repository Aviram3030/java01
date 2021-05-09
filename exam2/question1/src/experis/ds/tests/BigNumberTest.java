package experis.ds.tests;

import experis.ds.BigNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigNumberTest {

    @Test
    void backToString() {
        String s = "35823";
        BigNumber number = new BigNumber(s);

        assertEquals(s, number.getWord());
    }
}