package experis.ds.tests;

import experis.ds.BigNumber;
import experis.ds.Multiply;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest {

    @Test
    void multiply_test() {
        Multiply multiply = new Multiply();

        BigNumber num1 = new BigNumber("5");
        BigNumber num2 = new BigNumber("25");

        BigNumber multiplyNum = multiply.multiplyBigNumbers(num1, num2);

        assertEquals(125, multiplyNum.getNumber());

        assertNull(multiply.multiplyBigNumbers(null, num1));
        assertNull(multiply.multiplyBigNumbers(num1, null));
        assertNull(multiply.multiplyBigNumbers(null, null));
    }
}