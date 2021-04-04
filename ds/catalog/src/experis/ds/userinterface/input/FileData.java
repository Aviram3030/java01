package experis.ds.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileData implements Input{
    private File myObj;
    private Scanner myReader;

    public FileData(String fileName) throws FileNotFoundException {
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
