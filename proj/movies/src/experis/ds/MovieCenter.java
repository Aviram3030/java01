package experis.ds;

import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.gson.Movie;
import experis.ds.gson.MovieID;
import experis.ds.gson.TitleQueryResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 *  The MovieCenter class connects between the information
 *  of two different classes that uses omdbapi.
 *  Using thread pool for calling MovieQueryByID.
 *  The object is singleton.
 */

public class MovieCenter {
    private static MovieCenter movieCenter = new MovieCenter();
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private final MoviesQueryByTitle moviesQueryByTitle = new MoviesQueryByTitle();

    private MovieCenter(){
    }

    public static MovieCenter getMovieCenter(){
        return movieCenter;
    }

    /**
     *  The function searches for movies with name
     *  that contains title.
     *  Returns array that contains the data of every movie
     *  that got selected.
     */
    public Movie[] search(String title){
        title = getFixedTitle(title);
        TitleQueryResult result = moviesQueryByTitle.compute(title);
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
        Movie[] movies = new Movie[moviesID.length];
        Future<Movie>[] futures = getFutures(moviesID);

        for(int i = 0; i < futures.length; i++){
            try {
                movies[i] = futures[i].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    /**
     *  Adding tasks to the thread pool of type MovieQueryByID.
     */
    private Future<Movie>[] getFutures (MovieID[] moviesID){
        Future<Movie>[] futures = new Future[moviesID.length];
        for(int i = 0; i < futures.length; i++){
            String ID = moviesID[i].getImdbID();
            MovieQueryById queryById = new MovieQueryById(ID);
            futures[i] = forkJoinPool.submit(queryById);
        }
        return futures;
    }
}
