import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ListWithNoDuplicatesTest {
    @Test
    public void delete_duplicates_test() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1);
        list.add(5);
        list.add(6);
        list.add(1);
        list.add(1);
        list.add(6);

        ListWithNoDuplicates.deleteDuplicates(list);
        assertEquals(3, list.size());
        assertTrue(list.contains(1));
        assertTrue(list.contains(5));
        assertTrue(list.contains(6));

        list.add(7);
        ListWithNoDuplicates.deleteDuplicates(list);
        assertEquals(4, list.size());
        assertTrue( list.contains(7));
    }




}