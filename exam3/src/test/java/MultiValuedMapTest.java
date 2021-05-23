import org.junit.Test;

import static org.junit.Assert.*;

public class MultiValuedMapTest {

    @Test
    public void put() {
        var mm = new MultiValuedMap<Integer, Integer>();
        mm.put(4, 13);
        mm.put(7, 17);
        mm.put(4, 19);
        System.out.println("now key 4 is mapped to both values 13, 19");
        for(var e : mm.get(4)) {
            System.out.print(e + " ");
        }

        mm.put(4,17);
        System.out.println("\nnow key 4 is mapped to both values 13, 19, 17");
        for(var e : mm.get(4)) {
            System.out.print(e + " ");
        }
    }
}