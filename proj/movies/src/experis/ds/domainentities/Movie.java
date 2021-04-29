package experis.ds.domainentities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  The Movie class contains the data required
 *  that we need to present in the project.
 *  The object is created by Gson.
 */
public class Movie {
        @SerializedName("Title")
        private String title;

        @SerializedName("Genre")
        private String genre;

        @SerializedName("Ratings")
        private List<Rating> ratings;

        @SerializedName("Runtime")
        private String runtime;

        @SerializedName("Director")
        private String director;

        @SerializedName("Actors")
        private String actors;

        public String getTitle() {
                return title;
        }

        public String getGenre() {
                return genre;
        }

        public List<Rating> getRatings() {
                return ratings;
        }

        public String getRuntime() {
                return runtime;
        }

        public String getDirector() {
                return director;
        }

        public String getActors() {
                return actors;
        }

        public String toString(){
                return title + "|" +ratings + "|" + ratings.toString()+ "|" + runtime + "|" + director+ "|" + actors;
        }

}
