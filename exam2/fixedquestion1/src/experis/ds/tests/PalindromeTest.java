package experis.ds.tests;

import experis.ds.BigNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {

    @Test
    void palindrome_test() {
        BigNumber num = new BigNumber("25852");
        BigNumber num2 = new BigNumber("45385435");

        assertTrue(num.isPalindrome());
        assertFalse(num2.isPalindrome());
    }
}