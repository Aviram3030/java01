package experis.ds.tests;

import experis.ds.Ball;
import experis.ds.Generics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoveTest {

    @Test
    void remove_first_min() {
        List<Ball> a = new ArrayList<>();

        a.add(new Ball(1.1));
        a.add(new Ball(5.22));

        Ball b = new Ball(1);
        Ball c = new Ball(1);

        a.add(b);
        a.add(c);

        Ball removedElement = Generics.removeMin(a);
        assertTrue(removedElement == b);
        assertFalse(removedElement == c);

        for(int i = 0; i < 5; i++){
            a.add(new Ball(i));
        }

        for(int i = 10; i > 5; i--){
            a.add(new Ball(i));
        }

        Generics.removeMin(a); //removes 0
        removedElement = Generics.removeMin(a);
        assertTrue(removedElement == c);


    }
}