package experis.ds.database.books;

import experis.ds.database.creators.Author;
import experis.ds.database.creators.ISBN;
import experis.ds.database.creators.Publisher;

public class BookDetails {
    private final ISBN isbn;
    private final String title;
    private final Author author;
    private final int year;
    private final Publisher publisher;

    public BookDetails(ISBN isbn, String title, Author author, int year, Publisher publisher){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
    }


    public ISBN getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public Publisher getPublisher() {
        return publisher;
    }



}
