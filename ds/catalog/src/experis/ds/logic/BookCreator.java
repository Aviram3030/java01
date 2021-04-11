package experis.ds.logic;

import experis.ds.database.details.Book;
import experis.ds.database.details.BookDetails;
import experis.ds.database.fields.Author;
import experis.ds.database.fields.ISBN;
import experis.ds.database.fields.Publisher;

import java.util.HashMap;


public class BookCreator {

    private final HashMap<String,Author> authors = new HashMap<>();
    private final HashMap<String,Publisher> publishers = new HashMap<>();
    private final HashMap<String,ISBN> isbnElements = new HashMap<>();

    public Book create(BookDetails bookDetails) throws Exception {
        ISBN isbn = setISBN(bookDetails);
        String title = bookDetails.getTitle();
        Author author = makeAuthor(bookDetails);
        int year = bookDetails.getYear();
        Publisher publisher = makePublisher(bookDetails);

        Book book = new Book(isbn, title, author, year, publisher);
        author.addBook(book);
        publisher.addBook(book);
        isbn.addBook(book);

        return book;
    }

    public HashMap<String,ISBN> getIsbnElements(){
        return isbnElements;
    }

    private ISBN setISBN(BookDetails bookDetails) throws Exception {
        String isbnText = bookDetails.getIsbn();
        ISBN isbn = isbnElements.get(isbnText);
        if(isbn == null){
            isbn = new ISBN(isbnText);
            isbnElements.put(isbnText.toLowerCase(), isbn);
        }

        return isbn;
    }

    private Author makeAuthor(BookDetails bookDetails){
        String authorName = bookDetails.getAuthor();
        Author author = authors.get(authorName.toLowerCase());
        if(author == null) {
            author = new Author(authorName);
            authors.put(authorName.toLowerCase(), author);
        }

        return author;
    }

    private Publisher makePublisher(BookDetails bookDetails){
        String publisherName = bookDetails.getPublisher();
        Publisher publisher = publishers.get(publisherName.toLowerCase());
        if(publisher == null) {
            publisher = new Publisher(publisherName);
            publishers.put(publisherName.toLowerCase(), publisher);
        }

        return publisher;
    }
}

