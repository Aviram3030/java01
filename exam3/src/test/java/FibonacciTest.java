import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void calculate() {
        assertEquals(-1, Fibonacci.calculate(-4584));
        assertEquals(0, Fibonacci.calculate(0));
        assertEquals(1, Fibonacci.calculate(1));
        assertEquals(1, Fibonacci.calculate(2));

        assertEquals(55, Fibonacci.calculate(10));
        assertEquals(1597, Fibonacci.calculate(17));
        assertEquals(17711, Fibonacci.calculate(22));
    }
}