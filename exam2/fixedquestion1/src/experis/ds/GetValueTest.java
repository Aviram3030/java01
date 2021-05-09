package experis.ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetValueTest {

    @Test
    void value_test() {
        BigNumber num1 = new BigNumber("783477");
        assertEquals(783477, num1.getValue());

        BigNumber num2 = new BigNumber("-783477");
        assertEquals(-783477, num2.getValue());
    }
}