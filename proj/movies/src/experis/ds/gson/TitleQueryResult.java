package experis.ds.gson;

import com.google.gson.annotations.SerializedName;

public class TitleQueryResult {
    @SerializedName("Search")
    private MovieID[] search;

    public MovieID[] getSearch() {
        return search;
    }
}
