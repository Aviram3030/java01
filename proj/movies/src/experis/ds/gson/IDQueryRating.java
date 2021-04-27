package experis.ds.gson;

import com.google.gson.annotations.SerializedName;

public class IDQueryRating {
    @SerializedName("Source")
    private String source;

    @SerializedName("Value")
    private String value;

    public String getSource() {
        return source;
    }

    public String getValue() {
        return value;
    }
}
