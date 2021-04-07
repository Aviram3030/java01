package experis.ds.logic.query;

import experis.ds.database.fields.Author;
import experis.ds.database.fields.ISBN;
import experis.ds.database.fields.Publisher;

public class Book {
    private ISBN isbn;
    private String title;
    private Author author;
    private String year;
    private Publisher publisher;

    public Book(ISBN isbn, String title, Author author, String year, Publisher publisher) throws Exception {
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

    public String getYear() {
        return year;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
