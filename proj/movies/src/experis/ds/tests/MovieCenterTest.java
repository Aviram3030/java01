package experis.ds.tests;

import experis.ds.Display;
import experis.ds.MovieCenter;
import experis.ds.Output;
import experis.ds.gson.Movie;
import org.junit.jupiter.api.Test;

class MovieCenterTest {
    MovieCenter movieCenter = MovieCenter.getMovieCenter();
    @Test
    void search() {
        Movie[] movies = movieCenter.search("Star Wars");
        Output output = new Display();
        output.getOutput(movies);
    }
}