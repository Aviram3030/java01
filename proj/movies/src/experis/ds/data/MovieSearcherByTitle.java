package experis.ds.data;

import experis.ds.domainentities.TitleQueryResult;


public class MovieSearcherByTitle extends MovieSearcher {

    public MovieSearcherByTitle(String title){
        StringBuilder sb = new StringBuilder();
        sb.append("http://www.omdbapi.com/?apikey=b31ba527&s=");
        sb.append(title);
        sb.append("&");
        urlText = sb.toString();
    }

    public TitleQueryResult call(){
        String moviesByID = execute();
        return gson.fromJson(moviesByID, TitleQueryResult.class);
    }
}
