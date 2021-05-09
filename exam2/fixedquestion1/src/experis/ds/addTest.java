package experis.ds;

import static org.junit.jupiter.api.Assertions.*;

class addTest {

    @org.junit.jupiter.api.Test
    void add() {
        BigNumber num1 = new BigNumber("77");
        BigNumber num2 = new BigNumber("7");

        assertEquals(17375, num1.add(num1, num2).getValue());
    }
}