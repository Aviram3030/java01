package experis.ds.userinterface.output;

import experis.ds.domainentities.Movie;

public class DisplayConsole implements Display {
    DisplayMovie displayMovie = new DisplayMovie();
    public void getOutput(Movie[] movies){
        for(var movie: movies){
            displayMovie.print(movie);
        }
    }
}
