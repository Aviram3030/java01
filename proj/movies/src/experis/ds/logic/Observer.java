package experis.ds.logic;

import experis.ds.domainentities.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Observer {
        private final List<Movie> movies = new ArrayList<>();
        private int length;
        private boolean isRunning = true;

        public void addMovie(Movie movie){
            movies.add(movie);
            ++length;
        }

        public void finished(){
            isRunning = false;
        }

        public String toString() {
            return "Observer{" +
                    "\n Movies =" + movies.toString() +
                    "\n length =" + length +
                    "\n isRunning =" + isRunning +
                    '}';
        }
}
