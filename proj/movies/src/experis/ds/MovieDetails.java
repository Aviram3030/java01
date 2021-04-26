package experis.ds;

public class MovieDetails{
    private String title;
    private String genre;
    private String year;
    private String mpaa;
    private String duration;
    private String directors;
    private String stars;
    private String poster;

    public MovieDetails(String title, String genre, String year, String mpaa, String duration, String directors,
                        String stars, String poster){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.mpaa = mpaa;
        this.duration = duration;
        this.directors = directors;
        this.stars = stars;
        this.poster = poster;
    }
}
