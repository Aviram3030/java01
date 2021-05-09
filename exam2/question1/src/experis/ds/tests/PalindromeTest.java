package experis.ds.tests;

import experis.ds.BigNumber;
import experis.ds.Palindrome;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {

    @Test
    void palindrome_test() {
        Palindrome palindrome = new Palindrome();
        BigNumber num = new BigNumber("-523325");
        assertTrue(palindrome.isPalindrome(num));

        BigNumber num2 = new BigNumber("51225");
        assertFalse(palindrome.isPalindrome(num2));

        BigNumber num3 = new BigNumber("1");
        assertTrue(palindrome.isPalindrome(num3));

        assertFalse(palindrome.isPalindrome(null));

    }
}