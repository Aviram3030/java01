package com.tryfinally.sort;

import com.tryfinally.Point;
import com.tryfinally.PointComparator;
import com.tryfinally.PointComparatorByX;

public class SortedPointListByX extends SortByAscending{

    public SortedPointListByX(Point a){
        super(a, new PointComparatorByX());
    }

    public SortedPointListByX(PointComparator compare){
        super(compare);
    }

}
