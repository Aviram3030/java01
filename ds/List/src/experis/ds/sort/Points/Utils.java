package experis.ds.sort.Points;


import experis.ds.sort.Comparator;

public class Utils {
    public static Point max(Point a, Point b, Comparator cmp){
        return cmp.compare(a, b) < 0 ? b : a;
    }
}
