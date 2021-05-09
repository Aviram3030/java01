package experis.ds;

import static org.junit.jupiter.api.Assertions.*;

class addTest {

    @org.junit.jupiter.api.Test
    void add() {
        BigNumber num1 = new BigNumber("153425");
        BigNumber num2 = new BigNumber("7225");

        assertEquals(160650, BigNumber.add(num1, num2).getValue());

        BigNumber num3 = new BigNumber("153465436543654725");
        BigNumber num4 = new BigNumber("7356475647y6576547225");

        assertEquals(Integer.MAX_VALUE, BigNumber.add(num3, num4).getValue());

        BigNumber num5 = new BigNumber("7225");
        BigNumber num6 = new BigNumber("153425");

        assertEquals(160650, BigNumber.add(num5, num6).getValue());


    }
}