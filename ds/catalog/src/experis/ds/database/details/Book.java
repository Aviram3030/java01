package experis.ds.database.details;

import experis.ds.database.fields.Author;
import experis.ds.database.fields.ISBN;
import experis.ds.database.fields.Publisher;

public class Book {
    private final ISBN isbn;
    private final String title;
    private final Author author;
    private final int year;
    private final Publisher publisher;

    public Book(ISBN isbn, String title, Author author, int year, Publisher publisher) throws Exception {
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
