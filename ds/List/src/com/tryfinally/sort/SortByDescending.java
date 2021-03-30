package com.tryfinally.sort;


public class SortByDescending<T> implements Comparator<T> {

    private Comparator<T> comparator;

    public SortByDescending(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(T a, T b) {
        return comparator.compare(b, a);
    }
}
