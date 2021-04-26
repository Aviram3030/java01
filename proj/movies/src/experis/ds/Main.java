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

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        URL url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=b31ba527&s=harry");
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

        JSONParser parse = new JSONParser();
        JSONObject jObj = (JSONObject)parse.parse(inline.toString());
        JSONArray jsonarr_1 = (JSONArray) jObj.get("Title");

        for(int i = 0;i < jsonarr_1.size();i++)
        {
            JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
            System.out.println("Elements under results array");
            System.out.println("\nPlace id: " +jsonobj_1.get("place_id"));
            System.out.println("Types: " +jsonobj_1.get("types"));
        }

        sc.close();
    }
}


        /*Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close(); */



        /*URL url = new URL("http://www.omdbapi.com/?apikey=6c823ea6");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            scanner.close();

            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);
            JSONObject obj = (JSONObject) data_obj.get("Global");

            System.out.println(obj.get("TotalRecovered"));
        }*/

