package com.tryfinally;

public class PointComparatorByX implements PointComparator {
    @Override
    public int compare(Point a, Point b){
        return a.x - b.x;
    }
}
