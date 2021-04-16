package experis.ds.logic;

import experis.ds.database.details.Book;
import experis.ds.exceptions.IllegalQueryException;
import experis.ds.logic.query.authorpublisher.BooksExtractor;
import experis.ds.database.fields.*;

import experis.ds.logic.query.authorpublisher.AuthorPublisherQuery;
import experis.ds.logic.query.authorpublisher.BiFunc;
import experis.ds.logic.query.authorpublisher.Func;
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

        for (String s : titlesAndPublisher) {
            if (s.contains("publisher:")) {
                publisherName = s.substring(10);
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

        for (String s : titlesAndAuthor) {
            if (s.contains("author:")) {
                authorName = s.substring(7);
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
        Func<Publisher> func = Creator::getName;
        ArrayList<Publisher> selectedPublishers = publishersQuery.search(titles[0], biFunc, func);

        return BooksExtractor.extractBooks(selectedPublishers);
    }

    private ArrayList<Book> searchByAuthor(ArrayList<Book> books, String[] titles) {
        authorsQuery.set(books);

        BiFunc<Author,Book> biFunc = Book::getAuthor;
        Func<Author> func = Creator::getName;
        ArrayList<Author> selectedAuthors = authorsQuery.search(titles[0], biFunc, func);

        return BooksExtractor.extractBooks(selectedAuthors);
    }


    private ArrayList<Book> searchByIsbn(String text){
        ISBN isbn = isbnElements.get(text.toLowerCase());
        Book book = isbn.getBook();

        ArrayList<Book> selectedBook = new ArrayList<>();
        selectedBook.add(book);
        return selectedBook;
    }

    private ArrayList<Book> searchByTitle(ArrayList<Book> books, String[] queryTitles){
        TitleQuery titleQuery = new TitleQuery();
        titleQuery.load(queryTitles);

        return titleQuery.getBooksByTitle(books);
    }


}