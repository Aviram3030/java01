package experis.ds.input;

import java.util.Scanner;

public class ScannerInput extends Input{
    Scanner myObj = new Scanner(System.in);

    @Override
    public String line() {
        StringBuilder sb = new StringBuilder();

        while(!isEnd()) {
            sb.append(myObj.nextLine());
        }
        data = sb.toString();

        return data;
    }

    @Override
    public boolean isEnd() {
        return myObj.hasNext();
    }
}
