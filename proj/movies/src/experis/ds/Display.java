package experis.ds;

import experis.ds.gson.IDQueryRating;
import experis.ds.gson.Movie;

import java.util.List;

public class Display {
    public static void print(Movie[] movies){
        for(var movie: movies){
            System.out.printf("%s|", movie.getTitle());
            System.out.printf("%s|", movie.getGenre());
            List<IDQueryRating> ratings = movie.getRatings();
            for(IDQueryRating rating: ratings){
                System.out.printf("%s: %s, ", rating.getSource(), rating.getValue());
            }
            System.out.printf("|%s|", movie.getRuntime());
            System.out.printf("%s|", movie.getDirector());
            System.out.printf("%s\n", movie.getActors());
        }
    }
}
