package experis.ds.tests;

import com.google.gson.Gson;
import experis.ds.data.TitleQueryResult;
import experis.ds.logic.MovieSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchByTitleTest {
    Gson gson = new Gson();
    MovieSearcher movieSearcher = new MovieSearcher("s", "harry+potter");

    @Test
    void call(){
        String data = movieSearcher.call();
        TitleQueryResult result = gson.fromJson(data, TitleQueryResult.class);
        assertEquals("tt1201607",result.getMoviesIDs()[0].getImdbID());
        assertEquals("tt0241527",result.getMoviesIDs()[1].getImdbID());
        assertEquals("tt0295297",result.getMoviesIDs()[2].getImdbID());
        assertEquals("tt0304141",result.getMoviesIDs()[3].getImdbID());
        assertEquals("tt0330373",result.getMoviesIDs()[4].getImdbID());
    }
}