package experis.ds.userinterface.catalog;

import experis.ds.database.books.BookDetails;
import experis.ds.database.books.BooksList;
import experis.ds.database.books.IBooksList;
import experis.ds.logic.BookHarvester;
import experis.ds.logic.query.Library;
import experis.ds.userinterface.input.Input;

import java.util.Scanner;

public class Catalog {

    private Input input;

    public Catalog(Input input){
        this.input = input;
    }

    public void execute() {
        Scanner myReader = new Scanner(System.in);
        Library library = new Library(myReader);
        BookHarvester harvester = new BookHarvester(input);
        IBooksList booksList= new BooksList();

        BookDetails bookDetails = harvester.nextBook("\\|");

        while(harvester.hasNext()){
            if(bookDetails != null) {
                booksList.addBook(bookDetails);
            }
            bookDetails = harvester.nextBook("\\|");
        }

        library.setData(booksList);

        Boolean isValid = true;
        do{
            System.out.println("1.Search by ISBN");
            System.out.println("2.Search by title");
            System.out.println("3.Search by author");
            System.out.println("Press any other key to quit");

            if(!library.executeQueryType(myReader.nextLine(), booksList)){
                break;
            }
            System.out.println();

        } while(isValid);

    }

}
