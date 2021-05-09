package experis.ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterWeightCalculatorTest {

    @Test
    void getWeight() {
        int[] arr = new int[]{1, 2, 0, 1, 4, 3, 1, 2, 6, 1, 3};
        assertEquals(11, WaterWeightCalculator.getWeight(arr));

        int[] arr2 = new int[]{1, 2, 0, 1, 4, 3, 1, 2, 6, 1};
        assertEquals(9, WaterWeightCalculator.getWeight(arr2));

        int[] arr3 = new int[]{1,2,3,4,5};
        assertEquals(0, WaterWeightCalculator.getWeight(arr3));

        int[] arr4 = new int[]{5,4,3,2,1};
        assertEquals(0, WaterWeightCalculator.getWeight(arr4));

        int[] arr5 = new int[]{1, 2, 0, 1, 4, 3, 1, 2, 7, 5, 1, 2, 6, 3, 4, 1};
        assertEquals(20, WaterWeightCalculator.getWeight(arr5));
    }
}