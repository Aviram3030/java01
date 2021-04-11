package experis.ds.userinterface.catalog;

import experis.ds.database.details.BookDetails;
import experis.ds.database.details.Book;
import experis.ds.exceptions.IllegalQueryException;
import experis.ds.logic.BookCreator;
import experis.ds.logic.BookHarvester;
import experis.ds.logic.Library;
import experis.ds.userinterface.display.Display;
import experis.ds.userinterface.display.RegularDisplay;
import experis.ds.userinterface.display.SpecialDisplay;
import experis.ds.userinterface.input.Input;

import java.util.ArrayList;
import java.util.Scanner;
//make singleton

public class Catalog {

    private final Input input;
    Scanner reader = new Scanner(System.in);
    ArrayList<Book> books = new ArrayList<>();

    public Catalog(Input input){
        this.input = input;
    }

    public void start() {
        Library library = makeBooks();

        while(true){
            System.out.println("1.Search by ISBN");
            System.out.println("2.Search by title");
            System.out.println("3.Search by author");
            System.out.println("4.Search by publisher");
            System.out.println("5.Search by author and title");
            System.out.println("6.Search by publisher and title");
            System.out.println("type '.' to exit");

            String queryType = reader.nextLine();
            if(queryType.equals(".")){
                break;
            }

            try {
                ArrayList<Book> selectedBooks = library.executeQueryType(queryType);
                if(isEmptyOrNull(selectedBooks)){
                    System.out.println("Book not found");
                    continue;
                }
                printBooks(selectedBooks);
            }catch(IllegalQueryException e){
                e.printStackTrace();
            }

        }

    }

    private Boolean isEmptyOrNull(ArrayList<Book> selectedBooks){
        return selectedBooks == null || selectedBooks.size() == 0;
    }

    private Library makeBooks(){
        BookHarvester harvester = new BookHarvester(input);
        BookCreator bookCreator = new BookCreator();
        int counterIllegalBooks = 0;

        BookDetails bookDetails = harvester.nextBook("\\|");

        while(harvester.hasNext()){
            try {
                Book book = bookCreator.create(bookDetails);
                if(book != null) {
                    books.add(book);
                }

            } catch (Exception e) {
                counterIllegalBooks++;
            }
            bookDetails = harvester.nextBook("\\|");
        }

        if(counterIllegalBooks != 0){
            System.out.println("There were " + counterIllegalBooks + " illegal books in your catalog");
        }

        return new Library(reader, books, bookCreator.getIsbnElements());
    }

    private void printBooks(ArrayList<Book> selectedBooks){
        Display display = null;
        System.out.println("1.For regular display");
        System.out.println("2.For special display");
        String displayType = reader.nextLine();

        switch(displayType){
            case "1":
                display = new RegularDisplay();
                break;
            case "2":
                display = new SpecialDisplay();
                break;
            default:
        }

        display.print(selectedBooks);
        System.out.println();
    }

}
