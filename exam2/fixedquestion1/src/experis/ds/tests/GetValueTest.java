package experis.ds.tests;

import experis.ds.BigNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetValueTest {

    @Test
    void value_test() {
        BigNumber num1 = new BigNumber("783477");
        assertEquals(783477, num1.getValue());

        BigNumber num2 = new BigNumber("-783477");
        assertEquals(-783477, num2.getValue());

        BigNumber num3 = new BigNumber("-78345468753468754677");
        assertEquals(Integer.MIN_VALUE, num3.getValue());

        BigNumber num4 = new BigNumber("78345468753468754677");
        assertEquals(Integer.MAX_VALUE, num4.getValue());
    }
}