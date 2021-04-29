package experis.ds.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieCenterTest {

    @Test
    void call() {
        List<MovieCenter> list = makeList();
    }

    List<MovieCenter> makeList(){
        List<MovieCenter> list = new ArrayList<>();
        list.add(new MovieCenter(4, "Harry Potter"));
        list.add(new MovieCenter(4, "house"));
        list.add(new MovieCenter(4, "star wars"));
        list.add(new MovieCenter(4, "three days"));
        return list;
    }
}