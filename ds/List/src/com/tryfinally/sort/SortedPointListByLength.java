package com.tryfinally.sort;

import com.tryfinally.Point;
import com.tryfinally.PointComparator;
import com.tryfinally.PointComparatorByLength;

public class SortedPointListByLength extends SortByAscending{

    public SortedPointListByLength(Point a){
        super(a, new PointComparatorByLength());
    }

    public SortedPointListByLength(PointComparator compare){
        super(compare);
    }

}
