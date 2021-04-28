package experis.ds.data;

import experis.ds.domainentities.TitleQueryResult;


public class MovieSearcherByTitle extends MovieSearcher {

    public MovieSearcherByTitle(String title){
        StringBuilder sb = new StringBuilder();
        sb.append("http://www.omdbapi.com/?apikey=b31ba527&");
        sb.append("s");
        sb.append("=");
        sb.append(title);
        sb.append("&");
        urlText = sb.toString();
    }

    public void set(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://www.omdbapi.com/api/v2/?apikey=b31ba527&");
        sb.append("s");
        sb.append("=");
        sb.append(title);
        sb.append("&");
        urlText = sb.toString();
    }

    public TitleQueryResult call(){
        String moviesByID = execute();
        return gson.fromJson(moviesByID, TitleQueryResult.class);
    }
}
