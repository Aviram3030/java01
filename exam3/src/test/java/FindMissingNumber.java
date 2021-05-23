import org.junit.Test;

import static org.junit.Assert.*;

public class FindMissingNumber {

    @Test
    public void find_missing() {
        assertEquals(-1, MissingNumber.findMissingNumber(null));

        int[] arr1 = {1,3,5,4};
        assertEquals(2, MissingNumber.findMissingNumber(arr1));

        int[] arr2 = {9,5,3,4,1,7,6,2};
        assertEquals(8, MissingNumber.findMissingNumber(arr2));

        int[] arr3 = generateArray();
        assertEquals(73, MissingNumber.findMissingNumber(arr3));
    }

    //not the best test
    private int[] generateArray(){
        int[] v = new int[100];
        for(int i = 0; i < 72; i++){
            v[i] = i + 1;
        }

        for(int i = 72; i < v.length; i++){
            v[i] = i + 2;
        }
        return v;
    }
}