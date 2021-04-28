package experis.ds.userinterface.output;

import experis.ds.data.Movie;
import experis.ds.data.Rating;

import java.util.List;

public class DisplayConsole implements Display {
    public void getOutput(Movie[] movies){
        for(var movie: movies){
            System.out.printf("%s|", movie.getTitle());
            System.out.printf("%s|", movie.getGenre());
            List<Rating> ratings = movie.getRatings();
            for(Rating rating: ratings){
                System.out.printf("%s: %s, ", rating.getSource(), rating.getValue());
            }
            System.out.printf("\b\b|%s|", movie.getRuntime());
            System.out.printf("%s|", movie.getDirector());
            System.out.printf("%s\n", movie.getActors());
        }
    }
}
