package com.tryfinally.sort;

import com.tryfinally.sort.Points.Point;

public class SortTest {

    public static void testSortByX(){
        PointSubComparatorByX a = new PointSubComparatorByX();
        SortedPointList b = new SortedPointList(a);

        System.out.println("After insert:");
        createPoints(b);
    }

    public static void createPoints(SortedPointList a){
        for(int i = 10; i > 0; i--){
            a.insert(new Point( i, 10 - i));
        }

        a.insert(new Point( 5, 7));
        a.insert(new Point( 2, 8));
        a.insert(new Point( 11, 7));


        for(int i = 0; i < 10; i++){
            a.insert(new Point( i, 100 - i));
        }


        System.out.println(a.get().toString());

        System.out.println("After reverse:");

        a.reverse();

        System.out.println(a.get().toString());

        System.out.println("After reverse + insert:");

        a.insert(new Point( 4, 100));
        a.insert(new Point( 5, 100));
        a.insert(new Point( 6, 100));

        System.out.println(a.get().toString());

        System.out.println("After double reverse + insert:");

        a.reverse();

        a.insert(new Point( 8, 1000));
        a.insert(new Point( 2, 1000));
        a.insert(new Point( 5, 1000));

        System.out.println(a.get().toString());

    }
}
