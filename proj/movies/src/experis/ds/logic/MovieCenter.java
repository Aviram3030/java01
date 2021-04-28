package experis.ds.logic;

import experis.ds.data.MovieSearcherByID;
import experis.ds.data.MovieSearcherByTitle;
import experis.ds.domainentities.Movie;
import experis.ds.domainentities.MovieID;
import experis.ds.domainentities.TitleQueryResult;
import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.userinterface.output.DisplayMovie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *  The MovieCenter class connects between the information
 *  of two different classes that uses omdbapi.
 *  Using thread pool for calling MovieQueryByID.
 *  The object is singleton.
 */

public class MovieCenter implements Callable<Observer> {
    private final Observer observer = new Observer();
    private final ExecutorService executor;
    private String title;

    public MovieCenter(int numOfThreads, String title){
        executor = Executors.newFixedThreadPool(numOfThreads);
        this.title = title;
    }

    /**
     *  The function searches for movies with name
     *  that contains title.
     *  Returns array that contains the data of every movie
     *  that got selected.
     */
    @Override
    public Observer call(){
        if(title == null){
            throw new MovieNotFoundException("Input can't be null");
        }
        title = urlEscape(title);
        MovieSearcherByTitle movieSearcherByTitle = new MovieSearcherByTitle(title);

        TitleQueryResult result  = movieSearcherByTitle.call();  //getMoviesIDsByTitle
        MovieID[] moviesID = result.getMoviesIDs();

        if(moviesID == null){
            return null;
        }
        getMovies(moviesID);
        return observer;
    }

    /**
     *  Changing the title so we can use the query
     *  in the right way.
     */
    private String urlEscape(String title) {
        title = title.trim();
        return title.replace(' ', '+');
    }

    /**
     *  Waits for each thread to finish his operation and sending
     *  the data of the selected movies.
     *  Returns array that contains the data of every movie
     *  that got selected.
     */
    private void getMovies(MovieID[] moviesID) {
        List<Future<Movie>> futures = getFutures(moviesID);
        Movie[] movies = new Movie[moviesID.length];
        DisplayMovie displayMovie = new DisplayMovie();

        while(!futures.isEmpty()){
            for(int i = 0; i < futures.size(); i++){
                Future<Movie> future = futures.get(i);
                if(future.isDone()){
                    try {
                        Movie movie = future.get();
                        observer.addMovie(movie);
                        displayMovie.print(movie);
                        futures.remove(future);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
            observer.finished();
        }
    }

    /**
     *  Adding tasks to the thread pool of type query by id.
     */
    private List<Future<Movie>> getFutures (MovieID[] moviesID){
        List<Future<Movie>> futures = new ArrayList<>();
        for(int i = 0; i < moviesID.length ; i++) {
            String id = moviesID[i].getImdbID();
            MovieSearcherByID queryById = new MovieSearcherByID(id);
            futures.add(executor.submit(queryById));
        }
        return futures;
    }



}