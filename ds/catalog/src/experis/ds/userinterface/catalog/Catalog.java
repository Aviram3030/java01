package experis.ds.userinterface.catalog;

import experis.ds.logic.query.Library;
import experis.ds.userinterface.input.Input;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Catalog {

    private Input input;

    public Catalog(Input input){
        this.input = input;
    }

    public void execute() throws FileNotFoundException {
        Scanner myReader = new Scanner(System.in);
        Library library = new Library();
        library.setData(input);

        Boolean isValid = true;
        while(isValid) {
            System.out.println("1.Search by ISBN");
            System.out.println("2.Search by title");
            System.out.println("3.Search by author");
            System.out.println("Press any other key to quit");

            switch (myReader.nextLine()) {
                case "1" -> library.searchByIsbn();
                case "2" -> library.searchByTitle();
                case "3" -> library.searchByAuthor();
                default -> isValid = false;
            }
            System.out.println();
        }

    }

}
