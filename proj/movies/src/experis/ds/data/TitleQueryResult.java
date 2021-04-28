package experis.ds.data;

import com.google.gson.annotations.SerializedName;

/**
 *  Contains
 *  to present in the query.
 *  The object is created by Gson.
 */
public class TitleQueryResult {
    @SerializedName("Search")
    private MovieID[] moviesIDs;

    public MovieID[] getMoviesIDs() {
        return moviesIDs;
    }
}
