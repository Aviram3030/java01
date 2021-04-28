package experis.ds.logic;

import experis.ds.exceptions.InvalidCodeException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 *  The class MovieSearch make a query request by type to omdbapi
 *  and gets a string in json format.
 */
public class MovieSearcher implements Callable<String> {
    private final StringBuilder sb = new StringBuilder();
    private final String urlText;

    public MovieSearcher(String type, String query){
        sb.append("http://www.omdbapi.com/?apikey=b31ba527&");
        sb.append(type);
        sb.append("=");
        sb.append(query);
        sb.append("&");
        urlText = sb.toString();
        sb.setLength(0);
    }

    @Override
    public String call(){
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
