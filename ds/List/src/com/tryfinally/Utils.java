package com.tryfinally;


public class Utils {
    public static Point max(Point a, Point b, PointComparator cmp){
        return cmp.compare(a, b) < 0 ? b : a;
    }
}
