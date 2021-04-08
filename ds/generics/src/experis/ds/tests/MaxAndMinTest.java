package experis.ds.tests;

import experis.ds.*;
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

        List<Ball> b = Generics.maxAndMin(a.iterator());
        assertEquals(b.get(0).getArea(), 10);
        assertEquals(-8.8, b.get(1).getArea());

        Generics.removeMin(a);

        b = Generics.maxAndMin(a.iterator());
        assertEquals(b.get(0).getArea(), 10);
        assertEquals(b.get(1).getArea(), 0);
    }


    @Test
    void max_and_min_generator(){
        ListObjectsGenerator<Ball> a = new ListObjectsGenerator<>();

        Ball b = new Ball(5);
        Ball c = new Ball(2);

        IBiFuncObject<Ball> func= (x,y) -> new Ball(x.getArea() + y.getArea());

        List list = a.generator(b,c,3, func);

        List<Ball> d = Generics.maxAndMin(list.iterator());
        assertEquals(d.get(0).getArea(), 9);
        assertEquals(d.get(1).getArea(), 5);
    }
}