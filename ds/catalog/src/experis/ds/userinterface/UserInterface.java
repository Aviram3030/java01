package experis.ds.userinterface;

import experis.ds.exceptions.IllegalInputType;
import experis.ds.userinterface.catalog.Catalog;
import experis.ds.userinterface.input.InputFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {

    public static void start() {
        Scanner myReader = new Scanner(System.in);
        System.out.println("Hello, Which input would you like to apply?");
        System.out.println("1.Input file");
        try {
            switch (myReader.nextLine()) {
                case "1": {
                    System.out.println("Enter file name");
                    InputFile inputFile = new InputFile(myReader.nextLine());
                    new Catalog(inputFile).start();
                }
                default: throw new IllegalInputType("Illegal input type");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("Good bye");
    }

}
