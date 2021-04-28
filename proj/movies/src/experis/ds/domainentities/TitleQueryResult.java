package experis.ds.domainentities;

import com.google.gson.annotations.SerializedName;
import experis.ds.domainentities.MovieID;

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
