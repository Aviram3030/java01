package experis.ds.input;

import java.util.Scanner;

public class ScannerInput extends Input{
    Scanner myObj = new Scanner(System.in);

    @Override
    public String line() {
        System.out.println("Enter a string");
        StringBuilder sb = new StringBuilder();
        String line = "";

        while(true){
            if(line.equals(".")){
                line = myObj.nextLine();
                if(line == "\n"){
                    break;
                }
                sb.append(".");
                sb.append("\n");
            }

            line = myObj.nextLine();
            System.out.println(line);
            sb.append(line);
            sb.append("\n");
        }

        data = sb.toString();

        System.out.println("data is "+ data);
        return data;
    }

    @Override
    public boolean isEnd() {
        return myObj.hasNext();
    }
}
