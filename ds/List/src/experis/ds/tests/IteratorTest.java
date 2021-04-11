package experis.ds.tests;

import experis.ds.DoublyLinkedList;
import experis.ds.sort.Points.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

//merge testing
class IteratorTest {

    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    DoublyLinkedList<Point> points = new DoublyLinkedList<>();

    @BeforeEach
    void setUp() {
        for(int i = 0; i < 5; i++){
            list.addAtHead(i);
            points.addAtHead(new Point(i, 5-i));
        }
    }

    @Test
    void integerIterator() {
        Iterator<Integer> iterator = list.iterator();
        int counter = 0;
        while(iterator.hasNext()){
            assertEquals(counter++, iterator.next());
        }
        assertEquals(null, iterator.next());
    }

    @Test
    void pointIterator(){
        int counter = 0;
        Iterator<Point> iterator = points.iterator();
        while(iterator.hasNext()){
            Point point = iterator.next();
            assertEquals(counter, point.x);
            assertEquals(5 - counter, point.y);
            counter++;
        }
    }
}