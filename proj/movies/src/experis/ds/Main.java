package experis.ds;


import com.google.gson.Gson;
import experis.ds.gson.Movie;
import experis.ds.gson.TitleQueryResult;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void URL(String s) throws IOException {
        Gson gson = new Gson();
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

        TitleQueryResult result = gson.fromJson(inline.toString(), TitleQueryResult.class);
        System.out.println();
        String id = result.getSearch()[2].getImdbID();

        URLForId(id);
    }

    private static void URLForId(String s) throws IOException {
        Gson gson = new Gson();
        URL url = new URL("http://www.omdbapi.com/?apikey=b31ba527&i="+ s +"&");
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

        Movie result = gson.fromJson(inline.toString(), Movie.class);
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        URL("http://www.omdbapi.com/?apikey=b31ba527&s=star+wars&");
        //URL("http://www.omdbapi.com/?apikey=b31ba527&i=tt0080684&", "Title");

    }
}



