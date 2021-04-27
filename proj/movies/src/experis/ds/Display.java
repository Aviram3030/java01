package experis.ds;

import experis.ds.gson.Movie;
import experis.ds.gson.Rating;

import java.util.List;

public class Display implements Output{
    public void getOutput(Movie[] movies){
        for(var movie: movies){
            System.out.printf("%s|", movie.getTitle());
            System.out.printf("%s|", movie.getGenre());
            List<Rating> ratings = movie.getRatings();
            for(Rating rating: ratings){
                System.out.printf("%s: %s, ", rating.getSource(), rating.getValue());
            }
            System.out.printf("|%s|", movie.getRuntime());
            System.out.printf("%s|", movie.getDirector());
            System.out.printf("%s\n", movie.getActors());
        }
    }
}
