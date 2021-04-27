package experis.ds;

import com.google.gson.Gson;
import experis.ds.gson.TitleQueryResult;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MoviesQueryByTitle {
    private final Gson gson = new Gson();

    public TitleQueryResult compute(String query) {
        StringBuilder inLine = new StringBuilder();
        try {
            URL url = new URL("http://www.omdbapi.com/?apikey=b31ba527&s="+ query + "&");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            if(responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: "+responseCode);
            }

            Scanner sc = new Scanner(url.openStream());
            while(sc.hasNext())
            {
                inLine.append(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = inLine.toString();
        return gson.fromJson(result, TitleQueryResult.class);
    }

}
