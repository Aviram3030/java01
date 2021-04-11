package experis.ds.sort;


public class SortByAscending<T> implements Comparator<T> {

    private Comparator<T> comparator;

    public SortByAscending(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(T a, T b) {
        return comparator.compare(a, b);
    }

}
