package experis.ds.database.details;

import experis.ds.exceptions.IllegalBookDetailException;

public class BookDetails {
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private final String publisher;

    public BookDetails(String[] details) throws Exception {
        isbn = setISBN(details[0]);
        this.title = details[1];
        this.author = details[2];
        this.year = setYear(details[3]);
        this.publisher = details[4];
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    private String setISBN(String text) throws IllegalBookDetailException{
        if (text.length() < 8) {
            throw new IllegalBookDetailException();
        }

        return text;
    }

    private int setYear(String text) throws IllegalBookDetailException{
        int num;
        try {
            num = Integer.parseInt(text);
        }
        catch(Exception e){
            throw new IllegalBookDetailException();
        }
        if(num < 1970 || num > 2020){
            num = 1970;
        }

        return num;
    }


}
