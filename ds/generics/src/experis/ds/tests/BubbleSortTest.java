package experis.ds.tests;

import experis.ds.Ball;
import experis.ds.Generics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    @Test
    void bubble_sort() {
        List<Ball> a = new ArrayList<>();


        a.add(new Ball(1.1));
        a.add(new Ball(5.22));
        a.add(new Ball(-8.8));

        for(int i = 0; i < 5; i++){
            a.add(new Ball(i));
        }

        assertFalse(isSorted(a));
        Generics.bubbleSort(a);
        assertTrue(isSorted(a));

        for(int i = 10; i > 5; i--){
            a.add(new Ball(i));
        }
    }

    private Boolean isSorted(List<Ball> a){
        for(int i = 0; i < a.size() - 1; i++){
            if(a.get(i).compareTo(a.get(i + 1)) > 0){
                return false;
            }
        }
        return true;
    }
}