package experis.ds.userinterface;

import experis.ds.database.books.*;
import experis.ds.database.creators.ISBNList;
import experis.ds.logic.Query.TitleSearchCommands;
import experis.ds.userinterface.display.DisplayBook;
import experis.ds.userinterface.display.DisplayBooksList;
import experis.ds.userinterface.input.Input;
import experis.ds.database.creators.AuthorsNameList;
import experis.ds.database.creators.PublishersNameList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private Input input;
    private AuthorsNameList authors;
    private PublishersNameList publishers;
    private ISBNList isbnList;

    public Library(Input input){
        this.input = input;
    }

    public void execute() throws FileNotFoundException {
        Scanner myReader = new Scanner(System.in);
        IBookList books = new BookList();
        BookExtractorIsbn bookExtractorIsbn = new BookExtractorIsbn();
        BookExtractorAuthor bookExtractorAuthor = new BookExtractorAuthor();
        isbnList = new ISBNList();

        setData(bookExtractorIsbn,bookExtractorAuthor, books, isbnList);

        Boolean isValid = true;
        while(isValid) {
            System.out.println("1.Search by ISBN");
            System.out.println("2.Search by title");
            System.out.println("3.Search by author");
            System.out.println("Press any other key to quit");

            switch (myReader.nextLine()) {
                case "1" -> searchByIsbn(bookExtractorIsbn);
                case "2" -> searchByTitle(books);
                case "3" -> searchByAuthor(bookExtractorAuthor);
                default -> isValid = false;
            }
            System.out.println();
        }

    }

    private void setData(BookExtractorIsbn bookExtractorIsbn, BookExtractorAuthor bookExtractorAuthor, IBookList books, ISBNList isbnList) throws FileNotFoundException {
        String data = input.line();
        authors = new AuthorsNameList();
        publishers = new PublishersNameList();

        while(data != null){
            BookDetails book = BookWrapper.wrap(data, authors, publishers,"\\|");
            if(book != null) {
                books.addBook(book);
                bookExtractorIsbn.addBook(book);
                bookExtractorAuthor.addBook(book);
                isbnList.add(book.getIsbn());
            }
            data = input.line();
        }
    }

    private void searchByAuthor(BookExtractorAuthor bookExtractorAuthor) {
        Scanner myReader = new Scanner(System.in);
        System.out.print(">");
        DisplayBooksList display = new DisplayBooksList();
        String authorName = myReader.nextLine();
        ArrayList<BookDetails> books = bookExtractorAuthor.getBooks(authors.get(authorName));

        if(books == null){
            System.out.println("Book not found");
            return;
        }

        display.print(books);
    }

    private void searchByIsbn(BookExtractorIsbn bookExtractorIsbn){
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

    private void searchByTitle(IBookList books){
        Scanner myReader = new Scanner(System.in);
        ArrayList<BookDetails> booksDetails;
        TitleSearchCommands commands = new TitleSearchCommands();

        System.out.print(">");
        String getTitles = myReader.nextLine();
        String[] titles = getTitles.split(" ");
        for(String title: titles){
            commands.add(title);
        }

        booksDetails = books.searchBooks(commands);
        new DisplayBooksList().print(booksDetails);
    }

}
