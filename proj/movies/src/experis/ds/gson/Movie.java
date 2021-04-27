package experis.ds.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
        @SerializedName("Title")
        private String title;

        @SerializedName("Genre")
        private String genre;

        @SerializedName("Ratings")
        private List<IDQueryRating> ratings;

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

        public List<IDQueryRating> getRatings() {
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

}
