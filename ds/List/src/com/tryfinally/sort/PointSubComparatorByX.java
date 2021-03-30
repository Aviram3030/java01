package com.tryfinally.sort;

import com.tryfinally.sort.Points.Point;

public class PointSubComparatorByX implements Comparator<Point> {

    public int compare(Point a, Point b){
        return a.x - b.x;
    }


}
