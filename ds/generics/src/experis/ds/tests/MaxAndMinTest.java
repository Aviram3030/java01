package experis.ds.tests;

import experis.ds.Ball;
import experis.ds.Generics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxAndMinTest {

    @Test
    void max_and_min() {
        List<Ball> a = new ArrayList<>();

        a.add(new Ball(1.1));
        a.add(new Ball(5.22));
        a.add(new Ball(-8.8));

        for(int i = 0; i < 5; i++){
            a.add(new Ball(i));
        }

        Assertions.assertEquals(Generics.mid(a).getArea(), 1.0);

        for(int i = 10; i > 5; i--){
            a.add(new Ball(i));
        }

        assertEquals(Generics.mid(a).getArea(), 3.0);

        List<Ball> b = Generics.maxAndMin(a);
        assertEquals(b.get(0).getArea(), 10);
        assertEquals(-8.8, b.get(1).getArea());

        Generics.removeMin(a);

        b = Generics.maxAndMin(a);
        assertEquals(b.get(0).getArea(), 10);
        assertEquals(b.get(1).getArea(), 0);
    }


}