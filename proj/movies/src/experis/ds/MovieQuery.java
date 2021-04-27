package experis.ds;

import experis.ds.exceptions.InvalidCodeException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MovieQuery {
    public static String getData(String urlText){
        StringBuilder inLine = new StringBuilder();
        try {
            URL url = new URL(urlText);
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
        return inLine.toString();
    }
}
