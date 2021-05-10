package experis.ds.tests;

import experis.ds.BigNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest {

    @Test
    void multiply_test() {
        BigNumber num1 = new BigNumber("55");
        BigNumber num2 = new BigNumber("25");

        assertEquals(1375, BigNumber.multiply(num1, num2).getValue());

        BigNumber num3 = new BigNumber("100");
        BigNumber num4 = new BigNumber("55");

        assertEquals(5500, BigNumber.multiply(num3, num4).getValue());

        BigNumber num5 = new BigNumber("-14564");
        BigNumber num6 = new BigNumber("6467");

        assertEquals(-94_185_388, BigNumber.multiply(num5, num6).getValue());

    }
}