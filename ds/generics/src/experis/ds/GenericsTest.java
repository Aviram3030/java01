package experis.ds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class GenericsTest {
    private List<Integer> a = new ArrayList<>();

    @BeforeEach
    void setUp() {
        a.add(1);
        a.add(5);
        a.add(-8);

        for(int i = 0; i < 5; i++){
            a.add(i);
        }
        for(int i = 10; i > 5; i--){
            a.add(i);
        }
    }

    @Test
    void max() {
        assertTrue(Generics.max(a) == 10);
    }

    @Test
    void min() {
        assertTrue(Generics.min(a) == -8);
    }

    @Test
    void bubbleSort() {
        Generics.bubbleSort(a);
        isSorted(a);
    }

    @Test
    void mid() {
        assertTrue(Generics.mid(a) == 3);
    }

    @Test
    void removeMin() {
        Generics.removeMin(a);
        assertFalse(a.contains(-8));
        assertTrue(a.contains(5));
        assertTrue(a.size() == 12);

        while(a.size() != 0){
            Generics.removeMin(a);
        }
        Generics.removeMin(a);
    }

    @Test
    void maxAndMin() {
        List<Integer> b =Generics.maxAndMin(a);
        assertTrue(b.get(0) == 10);
        assertTrue(b.get(1) == -8);
    }

    private Boolean isSorted(List<Integer> a){
        for(int i = 0; i < a.size() - 1; i++){
            if(a.get(i).compareTo(a.get(i + 1)) > 0){
                return false;
            }
        }
        return true;
    }
}