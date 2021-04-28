package experis.ds.data;

import experis.ds.domainentities.Movie;

import java.util.concurrent.Callable;

public class MovieSearcherByID extends MovieSearcher implements Callable<Movie> {

    public MovieSearcherByID(String id){
        StringBuilder sb = new StringBuilder();
        sb.append("http://www.omdbapi.com/?apikey=b31ba527&");
        sb.append("i");
        sb.append("=");
        sb.append(id);
        sb.append("&");
        urlText = sb.toString();
    }

    @Override
    public Movie call(){
        String moviesByID = execute();
        return gson.fromJson(moviesByID, Movie.class);
    }

}
