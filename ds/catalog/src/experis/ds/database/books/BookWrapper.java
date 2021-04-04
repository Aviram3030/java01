package experis.ds.database.persons.books;

import experis.ds.database.author.Author;
import experis.ds.database.author.AuthorsNameList;
import experis.ds.database.author.Publisher;
import experis.ds.database.author.PublishersNameList;

public class BookWrapper {

    public static BookDetails wrap(String book, AuthorsNameList authors, PublishersNameList publishers,String separate){
        BookDetails bookDetails;
        String[] details = book.split(separate);

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
