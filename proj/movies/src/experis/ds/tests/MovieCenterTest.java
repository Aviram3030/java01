package experis.ds.tests;

import experis.ds.Display;
import experis.ds.MovieCenter;
import experis.ds.gson.Movie;
import org.junit.jupiter.api.Test;



class MovieCenterTest {
    MovieCenter movieCenter = MovieCenter.getInstance();
    @Test
    void search() {
        Movie[] movies = movieCenter.search("Star Wars");
        Display.print(movies);
    }
}