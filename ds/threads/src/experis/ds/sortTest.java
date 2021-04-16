package experis.ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class sortTest {

    @Test
    void sortArray() {
        int[] v = new int[]{8,5,3,56,1,9,42,4};
        Main.sortArray(v);
        assertTrue(isSorted(v));

        int[] a = new int[]{2};
        Main.sortArray(a);
        assertTrue(isSorted(a));

        int[] b = new int[]{54,18,5,7,5,2,-8};
        Main.sortArray(b);
        assertTrue(isSorted(b));
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