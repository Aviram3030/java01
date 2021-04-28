package experis.ds.data;

import com.google.gson.Gson;
import experis.ds.exceptions.InvalidCodeException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *  The class MovieSearch make a query request by type to omdbapi
 *  and gets a string in json format.
 */
abstract public class MovieSearcher{
    protected String urlText;
    protected Gson gson = new Gson();

    public String execute() {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlText);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new InvalidCodeException("HttpResponseCode: " + responseCode);
            }

            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
