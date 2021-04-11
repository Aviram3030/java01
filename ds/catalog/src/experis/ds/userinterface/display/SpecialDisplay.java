package experis.ds.userinterface.display;

import experis.ds.database.details.Book;

import java.util.ArrayList;

public class SpecialDisplay implements Display{

    @Override
    public void print(ArrayList<Book> books) {
        for(Book book: books) {
            System.out.println("ISBN: " + book.getIsbn().getText());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor().getName());
            System.out.println("Year: " + book.getYear());
            System.out.println("Publisher: " + book.getPublisher().getName());
            System.out.println("------------------------------");
        }
    }
}
