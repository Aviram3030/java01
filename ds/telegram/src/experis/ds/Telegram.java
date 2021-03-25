package experis.ds;

import experis.ds.encryption.*;
import experis.ds.input.InputFile;
import experis.ds.input.Input;
import experis.ds.input.ScannerInput;
import experis.ds.input.StringInput;
import experis.ds.output.OutputFile;
import experis.ds.output.OutputTerminal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Telegram {

    public static void run() throws IOException {
        System.out.println("Hello, which string manipulation would you like to apply?");
        System.out.print("UpperCase, LowerCase, ReplaceEncryption, ");
        System.out.println("XorEncryption or CaesarEncryption?");

        Scanner myObj = new Scanner(System.in);
        String s = myObj.nextLine();
        String data;
        while(!s.equals(".")){
            switch(s.toLowerCase()){
                case "uppercase": data = startUpperCase().execute();
                break;
                case "lowercase": data = startLowerCase().execute();
                break;
                case "replaceencryption": data = startReplaceEncryption().execute();
                break;
                case "xorencryption": data = startXorEncryption().execute();
                break;
                case "caesarencryption": data = startCaesarEncryption().execute();
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + s.toLowerCase());
            }

            if(data != null) {
                getOutput(data);
            }

        }
    }

    private static UpperCase startUpperCase() throws FileNotFoundException {
        return new UpperCase(insertInput());
    }

    private static LowerCase startLowerCase() throws FileNotFoundException {
        return new LowerCase(insertInput());
    }

    private static ReplaceEncryption startReplaceEncryption() throws FileNotFoundException {
        return new ReplaceEncryption(insertInput());

    }

    private static XorEncryption startXorEncryption() throws FileNotFoundException {
        return new XorEncryption(insertInput());
    }

    private static CaesarEncryption startCaesarEncryption() throws FileNotFoundException {
        return new CaesarEncryption(insertInput());
    }

    private static String insertInput() throws FileNotFoundException {
        System.out.println("For FileInput enter 1");
        System.out.println("For ScannerInput enter 2");
        System.out.println("For StringInput enter 3");

        Scanner myObj = new Scanner(System.in);
        Input input;
        switch(myObj.nextInt()) {
            case 1: input = new InputFile();
            break;
            case 2: input = new ScannerInput();
            break;
            case 3: input = new StringInput();
            break;
            default: input = null;
        }

        if(input != null) {
            return input.line();
        }

        return null;
    }

    private static void getOutput(String s) throws IOException {
        System.out.println("For outputFile enter 1");
        System.out.println("For outputTerminal enter 2");

        Scanner myObj = new Scanner(System.in);
        switch(myObj.nextInt()) {
            case 1: new OutputFile("output.txt").write(s);
            case 2: new OutputTerminal().outputTerminal(s);
            default: return;
        }
    }

}
