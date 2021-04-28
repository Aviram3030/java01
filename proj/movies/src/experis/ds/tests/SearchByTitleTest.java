package experis.ds.tests;

import com.google.gson.Gson;
import experis.ds.data.MovieSearcherByTitle;
import experis.ds.domainentities.TitleQueryResult;
import experis.ds.data.MovieSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchByTitleTest {
    Gson gson = new Gson();
    MovieSearcherByTitle movieSearcher = new MovieSearcherByTitle("harry+potter");

    @Test
    void call(){
        TitleQueryResult result = movieSearcher.call();
        assertEquals(10, result.getMoviesIDs().length);

        assertEquals("tt1201607",result.getMoviesIDs()[0].getImdbID());
        assertEquals("tt0241527",result.getMoviesIDs()[1].getImdbID());
        assertEquals("tt0295297",result.getMoviesIDs()[2].getImdbID());
        assertEquals("tt0304141",result.getMoviesIDs()[3].getImdbID());
        assertEquals("tt0330373",result.getMoviesIDs()[4].getImdbID());
    }
}