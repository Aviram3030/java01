package com.tryfinally.sort;

import experis.ds.DoublyLinkedList;
import experis.ds.Node;

public class SortedPointList<T> implements ISortedPointList<T>{
    private DoublyLinkedList<T> pointList;
    private Comparator comparator;

    public SortedPointList(Comparator compare){
        pointList = new DoublyLinkedList<T>();
        this.comparator = compare;
    }

    public SortedPointList(T point, Comparator compare){
        pointList = new DoublyLinkedList(point);
        this.comparator = compare;
    }


    public void insert(T point){
        int size = pointList.size();
        int i;
        for(i = 0; i < size ; i++){
            if(comparator.compare(point, pointList.get(i)) > 0){
                break;
            }
        }

        pointList.insert(point, i);
    }


     public void reverse(){
        comparator = new SortByDescending(comparator);
        reverseElements(pointList.size());
    }

    public void reverseElements(int n){
        if(n <= 1){
            return;
        }

        Node<T> a = pointList.popHead();
        reverseElements(n - 1);
        pointList.addTail(a);
    }

    public DoublyLinkedList<T> get(){
        return pointList;
    }

}
