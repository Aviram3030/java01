package experis.ds.userinterface.output;

import experis.ds.domainentities.Movie;

public class DisplayConsole implements Display<Movie[]> {
    DisplayMovie displayMovie = new DisplayMovie();

    @Override
    public void getOutput(Movie[] movies){
        for(var movie: movies){
            displayMovie.getOutput(movie);
        }
    }
}
