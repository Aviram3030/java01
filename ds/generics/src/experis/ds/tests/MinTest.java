package experis.ds.tests;

import experis.ds.Ball;
import experis.ds.Generics;
import experis.ds.ListNumbersGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinTest {

    @Test
    void first_min() {
        List<Ball> a = new ArrayList<>();

        assertNull(Generics.max(a));

        a.add(new Ball(1.1));
        a.add(new Ball(5.22));
        a.add(new Ball(-8.8));

        for(int i = 0; i < 5; i++){
            a.add(new Ball(i));
        }

        assertEquals(-8.8, Generics.min(a.iterator()).getArea());

        for(int i = -10; i < -5; i++){
            a.add(new Ball(i));
        }

        assertEquals(-10, Generics.min(a.iterator()).getArea());

    }

    @Test
    void first_min_generator(){
        ListNumbersGenerator a = new ListNumbersGenerator();


        List list = a.generator(5.0,2,3, (x,y) -> x.doubleValue() + y.doubleValue());

        assertEquals(Generics.min(list.iterator()), 5.0);
    }
}