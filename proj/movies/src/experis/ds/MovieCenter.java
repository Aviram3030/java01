package experis.ds;

import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.gson.Movie;
import experis.ds.gson.MovieID;
import experis.ds.gson.TitleQueryResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class MovieCenter {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private final MoviesQueryByTitle moviesQueryByTitle = new MoviesQueryByTitle();
    private static MovieCenter instance = new MovieCenter();

    private MovieCenter(){
    }

    public static MovieCenter getInstance(){
        return instance;
    }


    public Movie[] search(String title){
        title = getFixedTitle(title);
        TitleQueryResult result = moviesQueryByTitle.compute(title);
        MovieID[] moviesID = result.getSearch();
        if(moviesID == null){
            throw new MovieNotFoundException("Couldn't find a movie with this specific name");
        }
        return getMovies(moviesID);
    }

    private String getFixedTitle(String title) {
        title = title.trim();
        return title.replace(' ', '+');
    }

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
