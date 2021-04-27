package experis.ds;

import com.google.gson.Gson;
import experis.ds.gson.Movie;
import java.util.concurrent.RecursiveTask;

/**
 *  The class MovieQueryById make a query request by ID to omdbapi
 *  and gets a string in json format and with the help
 *  of Gson make Object of Movie class with the data in the string.
 */
public class MovieQueryById extends RecursiveTask<Movie> {
    private final String query;
    private final Gson gson = new Gson();
    public MovieQueryById(String query){
        this.query = query;
    }

    @Override
    protected Movie compute() {
        String urlText = "http://www.omdbapi.com/?apikey=b31ba527&i=" + query + "&";
        String info = MovieQuery.getData(urlText);
        return gson.fromJson(info, Movie.class);
    }

}
