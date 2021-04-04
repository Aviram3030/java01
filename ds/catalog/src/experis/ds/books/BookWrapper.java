package experis.ds.books;

import experis.ds.persons.Author;
import experis.ds.persons.AuthorsNameList;
import experis.ds.persons.Publisher;
import experis.ds.persons.PublishersNameList;

public class BookWrapper {

    public static BookDetails wrap(String book, AuthorsNameList authors, PublishersNameList publishers){
        BookDetails bookDetails;
        String[] details = book.split("\\|");

        if(details.length != 5){
            return null;
        }

        try {
            String isbn = details[0];
            String title = details[1];
            Author author = authors.add(details[2]);
            int year = Integer.parseInt(details[3]);
            Publisher publisher = publishers.add(details[4]);

            bookDetails = new BookDetails(isbn, title, author, year, publisher);
        }
        catch(Exception e){
            bookDetails = null;
        }

        return bookDetails;
    }

}
