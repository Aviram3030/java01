package experis.ds.gson;

import com.google.gson.annotations.SerializedName;

public class TitleQueryResult {
    @SerializedName("Search")
    private MovieID[] moviesIDs;

    public MovieID[] getMoviesIDs() {
        return moviesIDs;
    }
}
