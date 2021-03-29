package experis.ds.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputFile extends Input{

    private File myObj;
    Scanner myReader;

    public InputFile(){
        System.out.println("Enter file name");
        Scanner fileName = new Scanner(System.in);
        String s = fileName.nextLine();
        myObj = new File(s);
    }

    @Override
    public String line() throws FileNotFoundException {
        myReader = new Scanner(myObj);
        StringBuilder sb = new StringBuilder();

        while (isEnd()) {
           sb.append(myReader.nextLine());
           sb.append("\n");
        }

        return deleteLastLine(sb);
    }

    @Override
    public boolean isEnd() {
        return myReader.hasNext();
    }
}
