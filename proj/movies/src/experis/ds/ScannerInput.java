package experis.ds;

import java.util.Scanner;

public class ScannerInput implements Input{
    private Scanner reader;

    public ScannerInput(Scanner reader){
        this.reader = reader;
    }

    @Override
    public String getData(){
        return reader.nextLine();
    }
}
