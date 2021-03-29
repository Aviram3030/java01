package com.tryfinally;

public class PointComparatorByLength implements PointComparator{

    @Override
    public int compare(Point a, Point b) {
        return vectorLength(a) - vectorLength(b);
    }

    private int vectorLength(Point data){
        return square(data.x) + square(data.y);
    }

    private int square(int val){
        return val * val;
    }
}
