package experis.ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ball{
    private int price;

    public Ball(int price){
        this.price = price;
    }
}

class SecondQuestionTest {

    @Test
    void f2() {
        Ball B1 = new Ball(5);
        Ball B2 = new Ball(5);
        Ball B3 = new Ball(2);
        Ball B4 = new Ball(4);
        Ball B5 = new Ball(5);

        Ball[] arr1 = new Ball[]{B1,B2,B1,B4,B3,B5};
        Ball selectedBall = SecondQuestion.f2(arr1);
        assertEquals(B2, selectedBall);
        assertNotEquals(B1, selectedBall);

        Ball[] arr2 = new Ball[]{B5,B2,B1,B4,B2,B5};
        selectedBall = SecondQuestion.f2(arr2);
        assertEquals(B1, selectedBall);
        assertNotEquals(B2, selectedBall);

        Ball[] arr3 = new Ball[]{B3,B2,B1,B1,B2,B1,B3,B5};
        selectedBall = SecondQuestion.f2(arr3);
        assertEquals(B5, selectedBall);
        assertNotEquals(B1, selectedBall);

    }

    @Test
    void checkAllNumbersRepeating(){
        Ball B1 = new Ball(5);
        Ball B2 = new Ball(5);
        Ball B3 = new Ball(2);
        Ball B4 = new Ball(4);
        Ball B5 = new Ball(5);

        Ball[] arr1 = new Ball[]{B1,B1,B1,B3,B3,B1,B5,B2,B2,B5,B4,B4};
        assertEquals(null, SecondQuestion.f2(arr1));
    }

    @Test
    void checkNull(){
        assertNull(SecondQuestion.f2(null));
    }

    @Test
    void checkEmpty(){
        assertNull(SecondQuestion.f2(new Ball[]{}));
    }
}