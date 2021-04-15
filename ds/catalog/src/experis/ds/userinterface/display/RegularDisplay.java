package experis.ds.userinterface.display;

import experis.ds.database.details.Book;

import java.util.ArrayList;

public class RegularDisplay implements Display{

    @Override
    public void print(ArrayList<Book> books){
        for(Book book: books){
            System.out.print(book.getIsbn().getText() + "|" + book.getTitle() + "|" + book.getAuthor().getName());
            System.out.println("|" + book.getYear() + "|" + book.getPublisher().getName());
        }
    }
}
