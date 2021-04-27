package experis.ds;

import java.util.Scanner;

public class ScannerInput {
    private Scanner reader;

    public ScannerInput(Scanner reader){
        this.reader = reader;
    }

    public String getData(){
        return reader.nextLine();
    }
}
