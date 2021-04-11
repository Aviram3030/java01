package experis.ds.userinterface.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputFile implements Input{
    private final Scanner reader;

    public InputFile(String fileName) throws FileNotFoundException {
        File obj = new File(fileName);
        reader = new Scanner(obj);
        reader.nextLine();
    }

    @Override
    public String line(){
        return reader.nextLine();
    }

    @Override
    public Boolean hasNext() {
        return reader.hasNext();
    }
}
