package experis.ds.userinterface.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputFile implements Input{
    private File myObj;
    private Scanner myReader;

    public InputFile(String fileName) throws FileNotFoundException {
        myObj = new File(fileName);
        myReader = new Scanner(myObj);
        myReader.nextLine();
    }

    @Override
    public String line(){
        if(!myReader.hasNext()){
            return null;
        }

        return myReader.nextLine();
    }
}
