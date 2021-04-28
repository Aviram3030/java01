package experis.ds.userinterface.input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner reader;

    public ConsoleInput(Scanner reader){
        this.reader = reader;
    }

    @Override
    public String getData(){
        return reader.nextLine();
    }
}
