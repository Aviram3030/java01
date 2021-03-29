package experis.ds.input;

import java.util.Scanner;

public class ScannerInput extends Input{
    Scanner myObj = new Scanner(System.in);

    @Override
    public String line() {
        System.out.println("Enter input");
        data = getTerminalInput();

        return data;
    }

    public String getTerminalInput(){
        StringBuilder sb = new StringBuilder();
        String line = "";

        while(true){
            line = myObj.nextLine();

            if(line.equals("")) {
                line = myObj.nextLine();
                if (line.equals(".")) {
                    line = myObj.nextLine();
                    if (line.equals("")) {
                        break;
                    }
                }
            }

            sb.append(line);
            sb.append("\n");
        }

        return deleteLastLine(sb);
    }

    @Override
    public boolean isEnd() {
        return myObj.hasNext();
    }
}
