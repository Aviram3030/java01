package com.tryfinally.sort;

import com.tryfinally.Point;
import com.tryfinally.PointComparator;

public class SortByDescending extends SortedPointList{

    public SortByDescending(Point a, PointComparator compare) {
        super(a, compare);
    }

    public SortByDescending(PointComparator compare) {
        super(compare);
    }


    public int compareInteraction(Point a, Point b){
        return -compare.compare(a,b);
    }
}
