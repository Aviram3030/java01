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
    }
}