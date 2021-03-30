package com.tryfinally.sort.Points;


import com.tryfinally.sort.Comparator;

public class Utils {
    public static Point max(Point a, Point b, Comparator cmp){
        return cmp.compare(a, b) < 0 ? b : a;
    }
}
