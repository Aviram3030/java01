package com.tryfinally.sort;

import com.tryfinally.Point;

public class SortTest {

    public static void testSortByX(){
        SortedPointListByX a = new SortedPointListByX(new Point(21,5));
        createPoints(a);
        System.out.println(a.get().toString());
    }

    public static void createPoints(ISortedPointList a){
        for(int i = 10; i > 0; i--){
            a.insert(new Point(i, 10 - i));
        }

    }
}
