package experis.ds;

import java.net.MalformedURLException;
import java.net.URL;

public class MovieQueryById extends MoviesQuery {

    public MovieQueryById(String query){
        super(query);
    }

    protected URL getUrl() {
        URL url = null;
        try {
            url = new URL("http://www.omdbapi.com/?apikey=b31ba527&i="+ query + "&");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
