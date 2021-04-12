package experis.ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstQuestionTest {

    @Test
    void checkNumbers() {
        int[] arr1 = new int[]{7,7,1,2,1,2,8};
        assertEquals(8, FirstQuestion.f1(arr1));

        int[] arr2 = new int[]{5,4,2,1,3,8};
        assertEquals(5, FirstQuestion.f1(arr2));

        int[] arr3 = new int[]{100,65,42,100,65,23,55};
        assertEquals(42, FirstQuestion.f1(arr3));
    }

    @Test
    void checkAllNumbersRepeating(){
        int[] arr1 = new int[]{7,7,1,8,2,1,2,8,1,1,1,1,1};
        assertEquals(-1, FirstQuestion.f1(arr1));
    }

    @Test
    void checkNull(){
        assertEquals(-1, FirstQuestion.f1(null));
    }

    @Test
    void checkEmpty(){
        assertEquals(-1, FirstQuestion.f1(new int[5]));
    }
}