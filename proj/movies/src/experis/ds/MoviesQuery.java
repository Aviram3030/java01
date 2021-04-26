package experis.ds;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public abstract class MoviesQuery extends RecursiveTask<String> {
    protected String query;
    public MoviesQuery(String query){
        this.query = query;
    }

    @Override
    protected String compute() {
        StringBuilder inLine = new StringBuilder();
        try {
            URL url = getUrl();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
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

    abstract protected URL getUrl();

}
