package experis.ds.tests;

import experis.ds.logic.MovieCenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieCenterTest {
    MovieCenter movieCenter = new MovieCenter(4, "Star Wars");

    @Test
    void search() {
        /* var movies = movieCenter.call();
        assertEquals("Star Wars: Episode IV - A New Hope", movies[0].getTitle());
        assertEquals("Star Wars: Episode V - The Empire Strikes Back", movies[1].getTitle());
        assertEquals("Star Wars: Episode VI - Return of the Jedi", movies[2].getTitle());
        assertEquals("Star Wars: Episode VII - The Force Awakens", movies[3].getTitle());

        assertEquals("Action, Adventure, Fantasy, Sci-Fi", movies[0].getGenre());
        assertEquals("Action, Adventure, Fantasy, Sci-Fi", movies[1].getGenre());
        assertEquals("Action, Adventure, Fantasy, Sci-Fi", movies[2].getGenre());
        assertEquals("Action, Adventure, Sci-Fi", movies[3].getGenre()); */
    }
}