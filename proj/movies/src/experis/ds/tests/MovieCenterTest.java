package experis.ds.tests;

import experis.ds.MovieCenter;
import experis.ds.gson.IDQueryRating;
import experis.ds.gson.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;


class MovieCenterTest {

    MovieCenter movieCenter = new MovieCenter();
    @Test
    void search() {
        Movie[] movies = movieCenter.search("star wars");

        print(movies);
    }

    void print(Movie[] movies){
        for(var movie: movies){
            System.out.println("Title:" + movie.getTitle());
            System.out.println("Genre:" + movie.getGenre());
            System.out.println("Duration:" + movie.getRuntime());
            System.out.println("Directors:" + movie.getDirector());
            System.out.println("Actors:" + movie.getActors());
            List<IDQueryRating> ratings = movie.getRatings();
            for(IDQueryRating rating: ratings){
                System.out.println(rating.getSource() + " " + rating.getValue());
            }
            System.out.println("--------------------------------------------");
        }
    }
}