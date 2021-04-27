package experis.ds;

import com.google.gson.Gson;
import experis.ds.gson.TitleQueryResult;

/**
 *  The class MovieQueryById make a query request by title to omdbapi
 *  and gets a string in json format and with the help
 *  of Gson make Object of TitleQueryResult class with the data in the string.
 */
public class MoviesQueryByTitle {
    private final Gson gson = new Gson();

    public TitleQueryResult compute(String query) {
        String urlText = "http://www.omdbapi.com/?apikey=b31ba527&s="+ query + "&";
        String info = MovieQuery.getData(urlText);
        return gson.fromJson(info, TitleQueryResult.class);
    }

}
