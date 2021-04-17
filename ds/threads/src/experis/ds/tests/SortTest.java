package experis.ds.tests;

import experis.ds.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void firstTest() {
        int[] a = new int[]{8,5,3,56,1,9,42,4};
        Main.sortArray(a);
        assertTrue(isSorted(a));

    }

    @Test
    void secondTest(){
        int[] a = new int[]{2};
        Main.sortArray(a);
        assertTrue(isSorted(a));
    }

    @Test
    void thirdTest(){
        int[] a = new int[]{54,18,5,7,5,2,-8};
        Main.sortArray(a);
        assertTrue(isSorted(a));
    }

    private Boolean isSorted(int[] v){
        for(int i = 0; i < v.length - 1; i++){
            if(v[i] > v[i + 1]){
                return false;
            }
        }
        return true;
    }
}