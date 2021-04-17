package experis.ds.logic;

import experis.ds.database.details.Book;
import experis.ds.exceptions.IllegalQueryException;
import experis.ds.logic.query.authorpublisher.BooksExtractor;
import experis.ds.database.fields.*;

import experis.ds.logic.query.authorpublisher.AuthorPublisherQuery;
import experis.ds.logic.query.authorpublisher.BiFunc;
import experis.ds.logic.query.title.TitleQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {

    private final ArrayList<Book> booksList;
    private final Scanner reader;
    private final AuthorPublisherQuery<Author> authorsQuery = new AuthorPublisherQuery<>();
    private final AuthorPublisherQuery<Publisher> publishersQuery = new AuthorPublisherQuery<>();
    private final HashMap<String,ISBN> isbnElements;
    private final TitleQuery titleQuery = new TitleQuery();

    public Library(Scanner Reader, ArrayList<Book> booksList, HashMap<String, ISBN> isbnElements) {
        this.reader = Reader;
        this.isbnElements = isbnElements;
        this.booksList = booksList;
    }

    public ArrayList<Book> executeQueryType(String queryType) throws IllegalQueryException {
        System.out.print(">");
        String query;
        ArrayList<Book> selectedBooks;
        try {
            switch (queryType) {

                case "1" -> {
                    query = reader.nextLine().trim();
                    selectedBooks = searchByIsbn(query);
                }
                case "2" -> {
                    query = reader.nextLine().trim();
                    selectedBooks = searchByTitle(booksList, query.split(" "));
                }
                case "3" -> {
                    query = reader.nextLine().trim();
                    selectedBooks = searchByAuthor(booksList, query.split(" "));
                }
                case "4" -> {
                    query = reader.nextLine().trim();
                    selectedBooks = searchByPublisher(booksList, query.split(" "));
                }
                case "5" -> {
                    query = reader.nextLine().trim();
                    selectedBooks = searchByAuthorAndTitle(booksList, query.split(" "));
                }
                case "6" -> {
                    query = reader.nextLine().trim();
                    selectedBooks = searchByPublisherAndTitle(booksList, query.split(" "));
                }
                default -> throw new Exception();
            }
        }catch(Exception e){
            throw new IllegalQueryException();
        }

        return selectedBooks;
    }

    private ArrayList<Book> searchByPublisherAndTitle(ArrayList<Book> books, String[] titlesAndPublisher) {
        String[] titles = new String[titlesAndPublisher.length - 1];
        String publisherName = null;
        int k = 0;
        final int publisherLength = 10;

        for (String s : titlesAndPublisher) {
            if (s.contains("publisher:")) {
                publisherName = s.substring(publisherLength);
                continue;
            }
            titles[k++] = s;
        }

        ArrayList<Book> booksByPublisher = searchByPublisher(books, publisherName.split(" "));
        return searchByTitle(booksByPublisher, titles);
    }

    private ArrayList<Book> searchByAuthorAndTitle(ArrayList<Book> books, String[] titlesAndAuthor) {
        String[] titles = new String[titlesAndAuthor.length - 1];
        String authorName = null;
        int k = 0;
        final int authorLength = 7;

        for (String s : titlesAndAuthor) {
            if (s.contains("author:")) {
                authorName = s.substring(authorLength);
                continue;
            }
            titles[k++] = s;
        }

        ArrayList<Book> booksByAuthor = searchByAuthor(books, authorName.split(" "));
        return searchByTitle(booksByAuthor, titles);
    }

    private ArrayList<Book> searchByPublisher(ArrayList<Book> books, String[] titles) {
        publishersQuery.set(books);

        BiFunc<Publisher,Book> biFunc = Book::getPublisher;
        ArrayList<Publisher> selectedPublishers = publishersQuery.search(titles[0], biFunc);

        return getBooksForAuthorAndPublisher(selectedPublishers, publishersQuery);
    }

    private ArrayList<Book> searchByAuthor(ArrayList<Book> books, String[] titles) {
        authorsQuery.set(books);

        BiFunc<Author,Book> biFunc = Book::getAuthor;
        ArrayList<Author> selectedAuthors = authorsQuery.search(titles[0], biFunc);

        return getBooksForAuthorAndPublisher(selectedAuthors,authorsQuery);
    }

    private ArrayList<Book> getBooksForAuthorAndPublisher(ArrayList<? extends Creator> selectedCreators,
                                                          AuthorPublisherQuery<? extends  Creator> query){
        ArrayList<Book> selectedBooks = BooksExtractor.extractBooks(selectedCreators);
        query.clear();
        return selectedBooks;
    }


    private ArrayList<Book> searchByIsbn(String text){
        ISBN isbn = isbnElements.get(text.toLowerCase());
        Book book = isbn.getBook();

        ArrayList<Book> selectedBook = new ArrayList<>();
        selectedBook.add(book);
        return selectedBook;
    }

    private ArrayList<Book> searchByTitle(ArrayList<Book> books, String[] queryTitles){
        titleQuery.load(queryTitles);
        ArrayList<Book> selectedBooks = titleQuery.getBooksByTitle(books);
        titleQuery.clear();

        return selectedBooks;
    }


}
