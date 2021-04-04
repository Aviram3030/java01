package experis.ds.userinterface;

import experis.ds.userinterface.input.InputFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {

    public static void start() throws FileNotFoundException {
        Scanner myReader = new Scanner(System.in);
        System.out.println("Hello, Which input would you like to apply?");
        System.out.println("1.Input file");
        switch(myReader.nextLine()){
            case "1" : {
                System.out.println("Enter file name");
                new Library(new InputFile(myReader.nextLine())).execute();
            }
        }



    }

}
