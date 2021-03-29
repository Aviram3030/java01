package com.tryfinally.sort;

import com.tryfinally.Point;
import com.tryfinally.PointComparator;
import experis.ds.DoublyLinkedList;

abstract public class SortedPointList implements ISortedPointList{
    protected DoublyLinkedList<Point> pointList;
    protected PointComparator compare;

    public SortedPointList(PointComparator compare){
        pointList = new DoublyLinkedList<>();
        this.compare = compare;
    }

    public SortedPointList(Point point, PointComparator compare){
        pointList = new DoublyLinkedList<>(point);
        this.compare = compare;
    }


    public void insert(Point point){
        int size = pointList.size();
        for(int i = 0; i < size ; i++){
            System.out.println(i);
            if(compareInteraction(point, pointList.get(i)) > 0){
                pointList.insert(point, i);
                break;
            }
        }

        if(compareInteraction(point , pointList.getHead().getData()) > 0){
            pointList.addAtHead(point);
        }

    }

    public DoublyLinkedList<Point> get(){
        return pointList;
    }

    abstract protected int compareInteraction(Point a, Point b);
}
