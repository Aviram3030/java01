package experis.ds.logic;

import experis.ds.domainentities.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Observer {
        private final List<Movie> movies = new ArrayList<>();
        private boolean isRunning = true;
        private final AtomicLong start = new AtomicLong();
        private long periods;
        private long totalTimeExecution;
        private final AtomicLong failures = new AtomicLong();;
        private final List exceptions = new ArrayList(16);
        private long finishAfter;

        public void addMovie(Movie movie){
            movies.add(movie);
        }

        public void finished(){
            isRunning = false;
        }

        public void onStart(long activeFrom) {
            this.start.addAndGet(activeFrom);
        }

        public void onPeriodCompleted(long delta) {
            this.periods++;
            this.totalTimeExecution += delta;
        }

        public void onException(long delta, Exception x) {
            this.failures.incrementAndGet();
            this.exceptions.add(x);
        }

        public void onEnd(long overAllTime) {
            this.finishAfter = overAllTime;
        }

        public String toString() {
            return "StatsObserver{" +
                    "\n start=" + start.get() +
                    "\n periods=" + periods +
                    "\n totalTimeExecution=" + totalTimeExecution +
                    "\n failures=" + failures.get() +
                    "\n exceptions=" + exceptions +
                    "\n finishAfter=" + finishAfter +
                    '}';
        }
}
