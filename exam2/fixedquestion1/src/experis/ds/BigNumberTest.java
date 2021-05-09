package experis.ds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigNumberTest {

    @Test
    void string_test() {
        BigNumber num1 = new BigNumber("783477");
        assertEquals("783477", num1.getString());
    }
}