package experis.ds.userinterface.display;

import experis.ds.database.books.BookDetails;

public class DisplayBooksList {
    public void print(BookDetails book) {
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor().getName());
        System.out.println("Year: " + book.getYear());
        System.out.println("Publisher: " + book.getPublisher().getName());
    }
}
