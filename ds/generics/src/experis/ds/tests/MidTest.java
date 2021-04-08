package experis.ds.tests;

import experis.ds.Ball;
import experis.ds.Generics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MidTest {

    @Test
    void mid_element() {
        List<Ball> a = new ArrayList<>();

        assertNull(Generics.mid(a));

        a.add(new Ball(1.1));

        assertEquals(Generics.mid(a).getArea(), 1.1);

        a.add(new Ball(5.22));
        a.add(new Ball(-8.8));

        for(int i = 0; i < 5; i++){
            a.add(new Ball(i));
        }

        assertEquals(Generics.mid(a).getArea(), 1.0);

        for(int i = 10; i > 5; i--){
            a.add(new Ball(i));
        }


        assertEquals(Generics.mid(a).getArea(), 3.0);
    }
}