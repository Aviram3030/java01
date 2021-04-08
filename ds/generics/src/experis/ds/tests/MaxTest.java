package experis.ds.tests;

import experis.ds.Ball;
import experis.ds.Generics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxTest {

    @Test
    void last_max() {
        List<Ball> a = new ArrayList<>();

        assertNull(Generics.max(a));

        a.add(new Ball(1.1));
        a.add(new Ball(5.22));
        a.add(new Ball(-8.8));

        for(int i = 0; i < 5; i++){
            a.add(new Ball(i));
        }

        assertEquals(Generics.max(a).getArea(), 5.22);

        Ball b = new Ball(10);
        assertEquals(Generics.max(a), b);

        for(int i = 10; i > 5; i--){
            a.add(new Ball(i));
        }
        Ball c = new Ball(10);

        assertEquals(Generics.max(a), c);
    }
}