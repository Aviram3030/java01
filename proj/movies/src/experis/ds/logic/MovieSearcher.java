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
    private final String urlText;

    public MovieSearcher(String type, String query){
        StringBuilder sb = new StringBuilder();
        sb.append("http://www.omdbapi.com/?apikey=b31ba527&");
        sb.append(type);
        sb.append("=");
        sb.append(query);
        sb.append("&");
        urlText = sb.toString();
    }

    @Override
    public String call(){
        StringBuilder inLine = new StringBuilder();
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
                inLine.append(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inLine.toString();
    }
}
