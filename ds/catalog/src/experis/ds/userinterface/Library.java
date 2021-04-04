package experis.ds;

import experis.ds.database.books.*;
import experis.ds.database.persons.books.*;
import experis.ds.commands.TitleSearchCommands;
import experis.ds.input.FileData;
import experis.ds.input.Input;
import experis.ds.output.DisplayIsbnSearch;
import experis.ds.output.DisplayTitlesSearch;
import experis.ds.database.creators.AuthorsNameList;
import experis.ds.database.creators.PublishersNameList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private Scanner myReader;
    private Input input;

    public Library() throws FileNotFoundException {
        System.out.println("Enter File Name");
        myReader = new Scanner(System.in);
        input = new FileData(myReader.nextLine());
    }

    public void execute() throws FileNotFoundException {
        IBookList books = new BookList();
        BookExtractor bookExtractor = new BookExtractor();

        getData(bookExtractor, books);

        Boolean isValid = true;
        while(isValid) {
            System.out.println("1.Search by ISBN");
            System.out.println("2.Search By Title");
            System.out.println("Press any other key to quit");

            switch (myReader.nextLine()) {
                case "1" -> searchByIsbn(bookExtractor);
                case "2" -> searchByTitle(books);
                default -> isValid = false;
            }
            System.out.println();
        }

    }

    private void getData(BookExtractor bookExtractor, IBookList books) throws FileNotFoundException {
        String data = input.line();
        AuthorsNameList authors = new AuthorsNameList();
        PublishersNameList publishers = new PublishersNameList();

        while(data != null){
            BookDetails book = BookWrapper.wrap(data, authors, publishers,"\\|");
            if(book != null) {
                books.addBook(book);
                bookExtractor.addBook(book);
            }
            data = input.line();
        }
    }

    private void searchByIsbn(BookExtractor bookExtractor){
        System.out.print(">");
        DisplayIsbnSearch display = new DisplayIsbnSearch();
        String isbn = myReader.nextLine();
        BookDetails book = bookExtractor.getBook(isbn);

        if(book == null){
            System.out.println("Book not found");
            return;
        }

        display.print(book);
    }

    private void searchByTitle(IBookList books){
        ArrayList<BookDetails> booksDetails;
        TitleSearchCommands commands = new TitleSearchCommands();

        System.out.print(">");
        String getTitles = myReader.nextLine();
        String[] titles = getTitles.split(" ");
        for(String title: titles){
            commands.add(title);
        }

        booksDetails = books.searchBooks(commands);
        new DisplayTitlesSearch().print(booksDetails);
    }

}
