package experis.ds.userinterface.output;

import experis.ds.domainentities.Movie;
import experis.ds.domainentities.Rating;

import java.util.List;

public class DisplayMovie {
    public void print(Movie movie){
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
