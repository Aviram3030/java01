package experis.ds.logic.query;

import experis.ds.database.books.BookDetails;
import experis.ds.database.books.BooksList;
import experis.ds.database.books.IBooksList;
import experis.ds.database.fields.NamesList;
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
    private final BooksList booksList= new BooksList();
    private final Scanner myReader;

    public Library(Scanner myReader) {
        this.myReader = myReader;
    }

    public BooksList setData(IBooksList booksListDetails)  {
        for(int i = 0; i < booksListDetails.size(); i++){
            BookDetails bookDetails = booksListDetails.getBook(i);
            if(!addIsbn(bookDetails)){
                continue;
            }
            try {
                booksList.addBook(bookCreator(bookDetails));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return booksList;
    }

    private Book bookCreator(BookDetails bookDetails) throws Exception {
        ISBN isbn = new ISBN(bookDetails.getIsbn());
        String title = bookDetails.getTitle();
        Author author = addAuthor(bookDetails.getAuthor());
        String year = bookDetails.getYear();
        Publisher publisher = addPublisher(bookDetails.getPublisher());

        Book book = new Book(isbn, title, author, year, publisher);
        book.getAuthor().addBook(book);
        book.getPublisher().addBook(book);

        return book;
    }

    public Boolean executeQueryType(String query,BooksList booksList){
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

    private Author addAuthor(String authorName){
        authors.add(authorName, (x)-> new Author(x));
        return authors.get(authorName);
    }

    private Publisher addPublisher(String publisherName){
        publishers.add(publisherName, (x)-> new Publisher(x));
        return publishers.get(publisherName);
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
        Author author = authors.get(authorName);
        ArrayList<Book> books = author.getBooks();

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

    private void searchByTitle(BooksList booksList){
        ArrayList<Book> books;
        TitleSearchCommands commands = new TitleSearchCommands();

        String getTitles = myReader.nextLine();
        String[] titles = getTitles.split(" ");

        for(String title: titles){
            commands.add(title);
        }

        books = booksList.getBooks(commands);
        new DisplayBooksList().print(books);
    }
}
