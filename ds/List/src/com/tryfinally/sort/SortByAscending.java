package com.tryfinally.sort;

import com.tryfinally.Point;
import com.tryfinally.PointComparator;

public class SortByAscending extends SortedPointList{

    public SortByAscending(Point a, PointComparator compare) {
        super(a, compare);
    }

    public SortByAscending(PointComparator compare) {
        super(compare);
    }

    public int compareInteraction(Point a, Point b){
        return compare.compare(a,b);
    }
}
