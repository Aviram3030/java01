package experis.ds.tests;

import experis.ds.BigNumber;
import experis.ds.Sum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {

    @Test
    void addNumbers() {
        Sum sum = new Sum();

        BigNumber num1 = new BigNumber("5");
        BigNumber num2 = new BigNumber("25");

        BigNumber multiplyNum = sum.addBigNumbers(num1, num2);

        assertEquals(30, multiplyNum.getNumber());

        assertNull(sum.addBigNumbers(null, num1));
        assertNull(sum.addBigNumbers(num1, null));
        assertNull(sum.addBigNumbers(null, null));
    }
}