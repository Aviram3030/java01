package com.tryfinally.sort;

import com.tryfinally.Point;
import com.tryfinally.PointComparator;
import com.tryfinally.PointComparatorByLength;

public class SortedPointListByDescendingLength extends SortByDescending{

    public SortedPointListByDescendingLength(Point a, PointComparator compare){
        super(a, new PointComparatorByLength());
    }

    public SortedPointListByDescendingLength(PointComparator compare){
        super(compare);
    }
}
