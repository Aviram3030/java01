package experis.ds.tests;

import experis.ds.TheContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TheContainerTest {

    @Test
    void edgeCases() {
        TheContainer<Integer> container = new TheContainer<>();
        container.push(2);
        assertEquals(2, container.min());
        assertEquals(2, container.pop());
        container.push(2);
        assertEquals(2, container.min());
        container.push(2);
        container.push(1);
        assertEquals(1, container.min());

        container.forEach((x) -> System.out.println(x)); //checks if it doesn't shuffle them

        assertEquals(1, container.pop());
        assertEquals(2, container.min());
        assertEquals(2, container.pop());
        assertEquals(2, container.min());
        container.pop();
        assertNull(container.min());
    }

    @Test
    void pushAndPop(){
        TheContainer<Integer> container = new TheContainer<>();
        container.push(3);
        for(int i = 0; i < 5; i++){
            container.push(i);
        }

        container.forEach((x) -> System.out.println(x)); //checks if it doesn't shuffle them

        assertEquals(4, container.pop());
        assertEquals(0, container.min());
        assertEquals(3, container.pop());
        assertEquals(0, container.min());
        assertEquals(2, container.pop());
        assertEquals(0, container.min());
        assertEquals(1, container.pop());
        assertEquals(0, container.pop());

        container.push(1);

        assertEquals(1, container.min());
        assertEquals(1, container.pop());
        assertEquals(3, container.min());
        assertEquals(3, container.pop());
        assertNull(container.min());
        assertNull(container.pop());
    }
}