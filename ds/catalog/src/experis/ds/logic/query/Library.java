package experis.ds.logic.query;

import experis.ds.database.books.BookDetails;
import experis.ds.database.books.BooksList;
import experis.ds.database.books.IBooksList;
import experis.ds.database.fields.AuthorsNameList;
import experis.ds.database.fields.ISBNList;
import experis.ds.database.fields.PublishersNameList;

import experis.ds.logic.display.DisplayBook;
import experis.ds.logic.display.DisplayBooksList;
import experis.ds.userinterface.input.Input;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private AuthorsNameList authors = new AuthorsNameList();
    private PublishersNameList publishers = new PublishersNameList();
    private ISBNList isbnList = new ISBNList();
    private BookExtractorIsbn bookExtractorIsbn = new BookExtractorIsbn();
    private BookExtractorAuthor bookExtractorAuthor = new BookExtractorAuthor();
    private IBooksList books = new BooksList();

    public void setData(Input input) throws FileNotFoundException {
        String data = input.line();

        while(data != null){
            BookDetails book = BookHarvester.harvest(data, authors, publishers,"\\|");
            if(book != null) {
                books.addBook(book);
                bookExtractorIsbn.addBook(book);
                bookExtractorAuthor.addBook(book);
                isbnList.add(book.getIsbn());
            }
            data = input.line();
        }
    }

    public void searchByAuthor() {
        Scanner myReader = new Scanner(System.in);
        System.out.print(">");
        DisplayBooksList display = new DisplayBooksList();
        String authorName = myReader.nextLine();
        ArrayList<BookDetails> books = bookExtractorAuthor.getBooks(authors.add(authorName));

        if(books == null){
            System.out.println("Book not found");
            return;
        }

        display.print(books);
    }

    public void searchByIsbn(){
        Scanner myReader = new Scanner(System.in);
        System.out.print(">");
        DisplayBook display = new DisplayBook();
        String isbn = myReader.nextLine();
        BookDetails book = bookExtractorIsbn.getBook(isbnList.get(isbn));

        if(book == null){
            System.out.println("Book not found");
            return;
        }

        display.print(book);
    }

    public void searchByTitle(){
        Scanner myReader = new Scanner(System.in);
        ArrayList<BookDetails> booksDetails;
        TitleSearchCommands commands = new TitleSearchCommands();

        System.out.print(">");
        String getTitles = myReader.nextLine();
        String[] titles = getTitles.split(" ");

        for(String title: titles){
            commands.add(title);
        }

        booksDetails = books.getBooks(commands);
        new DisplayBooksList().print(booksDetails);
    }
}
