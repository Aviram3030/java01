package experis.ds.output;

import experis.ds.books.BookDetails;

public class DisplayIsbnSearch {
    public void print(BookDetails book) {
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Year: " + book.getYear());
        System.out.println("Publisher: " + book.getPublisher());
    }
}
