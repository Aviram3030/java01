package experis.ds;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}


public class Main {

    public static void URL(String s) throws IOException {
        URL url = new URL(s);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.connect();
        int responseCode = con.getResponseCode();
        Scanner sc = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while(sc.hasNext())
        {
            inline.append(sc.nextLine());
        }
        System.out.println("\nJSON data in string format");
        System.out.println(inline);
    }

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        URL("http://www.omdbapi.com/?apikey=b31ba527&s=star_wars&");
        URL("http://www.omdbapi.com/?apikey=b31ba527&i=tt0080684&");
        URL url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=b31ba527&s=star_wars&");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.connect();
        int responseCode = con.getResponseCode();
        Scanner sc = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while(sc.hasNext())
        {
            inline.append(sc.nextLine());
        }
        System.out.println("\nJSON data in string format");
        System.out.println(inline);


        //gson.fromJson(jsonString, MovieDetails[] movies);

        sc.close();
    }
}



