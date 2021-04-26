package experis.ds;

import java.net.MalformedURLException;
import java.net.URL;

public class MoviesQueryByTitle extends MoviesQuery {

    public MoviesQueryByTitle(String query) {
        super(query);
    }

    @Override
    protected URL getUrl() {
        URL url = null;
        try {
            url = new URL("http://www.omdbapi.com/?apikey=b31ba527&s="+ query + "&");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
