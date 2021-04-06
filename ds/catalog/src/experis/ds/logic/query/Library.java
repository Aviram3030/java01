package experis.ds.logic.query;

import experis.ds.database.books.BookDetails;
import experis.ds.database.books.IBooksList;
import experis.ds.database.books.NamesList;
import experis.ds.database.fields.*;

import experis.ds.logic.display.DisplayBook;
import experis.ds.logic.display.DisplayBooksList;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private final NamesList<String,Author> authors = new NamesList<>();
    private final NamesList<String,Publisher> publishers = new NamesList<>();
    private final ISBNList isbnList = new ISBNList();
    private final BookExtractorIsbn bookExtractorIsbn = new BookExtractorIsbn();
    private final BookExtractorAuthor bookExtractorAuthor = new BookExtractorAuthor();
    private final Scanner myReader;

    public Library(Scanner myReader) {
        this.myReader = myReader;
    }

    public void setData(IBooksList booksList)  {
        for(int i = 0; i < booksList.size(); i++){
            BookDetails bookDetails = booksList.getBook(i);
            if(!addIsbn(bookDetails)){
                continue;
            }
            addAuthor(bookDetails.getAuthor().toLowerCase(), bookDetails);
            addPublisher(bookDetails.getPublisher());
        }
    }

    public Boolean executeQueryType(String query,IBooksList booksList){
        switch (query) {
            case "1" -> {
                System.out.print(">");
                searchByIsbn();
                return true;
            }
            case "2" -> {
                System.out.print(">");
                searchByTitle(booksList);
                return true;
            }
            case "3" ->{
                System.out.print(">");
                searchByAuthor();
                return true;
            }
        }
        return false;
    }

    private void addAuthor(String authorName, BookDetails bookDetails){
        authors.add(authorName, (x)-> new Author(x));
        Author author = authors.get(authorName);
        bookExtractorAuthor.addBook(bookDetails, author);
    }

    private void addPublisher(String publisherName){
        publishers.add(publisherName, (x)-> new Publisher(x));
        Publisher publisher = publishers.get(publisherName);
    }

    private Boolean addIsbn(BookDetails bookDetails){
        ISBN isbn;
        try {
            isbn = new ISBN(bookDetails.getIsbn());
        } catch (Exception e) {
            return false;
        }
        bookExtractorIsbn.addBook(bookDetails, isbn);
        isbnList.add(isbn);
        return true;
    }

    private void searchByAuthor() {
        DisplayBooksList display = new DisplayBooksList();
        String authorName = myReader.nextLine();
        authors.add((authorName.toLowerCase()),(x)-> new Author(x));
        ArrayList<BookDetails> books = bookExtractorAuthor.getBooks(authors.get((authorName.toLowerCase())));

        if(books == null){
            System.out.println("Book not found");
            return;
        }

        display.print(books);
    }

    private void searchByIsbn(){
        DisplayBook display = new DisplayBook();
        String isbn = myReader.nextLine();
        BookDetails book = bookExtractorIsbn.getBook(isbnList.get(isbn));

        if(book == null){
            System.out.println("Book not found");
            return;
        }

        display.print(book);
    }

    private void searchByTitle(IBooksList booksList){
        ArrayList<BookDetails> booksDetails;
        TitleSearchCommands commands = new TitleSearchCommands();

        String getTitles = myReader.nextLine();
        String[] titles = getTitles.split(" ");

        for(String title: titles){
            commands.add(title);
        }

        booksDetails = booksList.getBooks(commands);
        new DisplayBooksList().print(booksDetails);
    }
}
