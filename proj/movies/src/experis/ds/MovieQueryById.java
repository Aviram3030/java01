package experis.ds;

import com.google.gson.Gson;
import experis.ds.exceptions.InvalidCodeException;
import experis.ds.gson.Movie;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class MovieQueryById extends RecursiveTask<Movie> {
    private final String query;
    private final Gson gson = new Gson();
    public MovieQueryById(String query){
        this.query = query;
    }
    @Override
    protected Movie compute() {
        StringBuilder inLine = new StringBuilder();
        try {
            URL url = new URL("http://www.omdbapi.com/?apikey=b31ba527&i="+ query + "&");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            if(responseCode != 200) {
                throw new InvalidCodeException("HttpResponseCode: "+responseCode);
            }

            Scanner sc = new Scanner(url.openStream());
            while(sc.hasNext())
            {
                inLine.append(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String info = inLine.toString();
        return gson.fromJson(info, Movie.class);
    }

}
