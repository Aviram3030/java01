package experis.ds.logic;

import com.google.gson.Gson;
import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.data.Movie;
import experis.ds.data.MovieID;
import experis.ds.data.TitleQueryResult;

import java.util.concurrent.*;

/**
 *  The MovieCenter class connects between the information
 *  of two different classes that uses omdbapi.
 *  Using thread pool for calling MovieQueryByID.
 *  The object is singleton.
 */

public class MovieCenter {
    private final static Gson gson = new Gson();
    private final ExecutorService executor;

    public MovieCenter(int numOfThreads){
        executor = Executors.newFixedThreadPool(numOfThreads);
    }

    /**
     *  The function searches for movies with name
     *  that contains title.
     *  Returns array that contains the data of every movie
     *  that got selected.
     */
    public Movie[] search(String title){
        if(title == null){
            throw new MovieNotFoundException("Input can't be null");
        }
        title = getFixedTitle(title);
        MovieSearcher titleQuery = new MovieSearcher("s", title);

        String moviesByTitle = titleQuery.call();
        TitleQueryResult result = gson.fromJson(moviesByTitle, TitleQueryResult.class);
        MovieID[] moviesID = result.getMoviesIDs();

        if(moviesID == null){
            throw new MovieNotFoundException("Couldn't find a movie with this specific name");
        }
        return getMovies(moviesID);
    }

    /**
     *  Changing the title so we can use the query
     *  in the right way.
     */
    private String getFixedTitle(String title) {
        title = title.trim();
        return title.replace(' ', '+');
    }

    /**
     *  Waits for each thread to finish his operation and sending
     *  the data of the selected movies.
     *  Returns array that contains the data of every movie
     *  that got selected.
     */
    private Movie[] getMovies(MovieID[] moviesID) {
        Future<String>[] futures = getFutures(moviesID);
        Movie[] movies = new Movie[moviesID.length];

        for(int i = 0; i < futures.length; i++){
            try {
                String moviesByID = futures[i].get();
                movies[i] = gson.fromJson(moviesByID, Movie.class);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    /**
     *  Adding tasks to the thread pool of type query by id.
     */
    private Future<String>[] getFutures (MovieID[] moviesID){
        Future<String>[] futures = new Future[moviesID.length];
        for(int i = 0; i < futures.length; i++){
            String id = moviesID[i].getImdbID();
            MovieSearcher queryById = new MovieSearcher("i", id);
            futures[i] = executor.submit(queryById);
        }
        return futures;
    }
}
