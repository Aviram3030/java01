package experis.ds.books;

import experis.ds.persons.Author;
import experis.ds.persons.Publisher;

public class BookDetails {
    private final String isbn;
    private final String title;
    private final Author author;
    private final int year;
    private final Publisher publisher;

    public BookDetails(String isbn, String title, Author author, int year, Publisher publisher){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
    }


    public String getIsbn() {
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
