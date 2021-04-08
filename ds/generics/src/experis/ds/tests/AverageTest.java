package experis.ds.tests;

import experis.ds.Generics;
import experis.ds.ListNumbersGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AverageTest {

    @Test
    void average_test() {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(5);
        a.add(-8);

        for(int i = 0; i < 5; i++){
            a.add(i);
        }

        for(int i = 10; i > 5; i--){
            a.add(i);
        }

        double b = Generics.average(a);
        assertEquals((int) b, 3);
        assertEquals(b, calculateAverage(a));
    }

    @Test
    void average_test_generator(){
        ListNumbersGenerator a = new ListNumbersGenerator();


        List list = a.generator(5.0,2,3, (x,y) -> x.doubleValue() + y.doubleValue());

        assertEquals(Generics.average(list), 7.0);
    }

    double calculateAverage(List<Integer> a){
        double sum = a.get(0);
        for(int i = 1; i < a.size(); i++){
            sum += a.get(i);
        }
        sum /= a.size();
        return sum;
    }
}