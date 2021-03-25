package experis.ds;

import experis.ds.input.FileInput;
import experis.ds.input.Input;
import experis.ds.input.ScannerInput;
import experis.ds.input.StringInput;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Telegram {
    public Telegram(){
    }

    public void run(){
        System.out.println("Hello, which string manipulation would you like to apply?");
        System.out.print("UpperCase, LowerCase , ReplaceEncryption,");
        System.out.println("XorEncryption or CaesarEncryption?");

        Scanner myObj = new Scanner(System.in);
        String s = myObj.nextLine();
        while(!s.equals(".")){
            switch(s.toLowerCase()){
                case "uppercase": startUpperCase();
                case "lowercase": startLowerCase();
                case "replaceencryption": startReplaceEncryption();
                case "xorencryption": startXorEncryption();
                case "caesarencryption": startCaesarEncryption();
            }

        }
    }

    private void startUpperCase() {
    }

    private void startLowerCase() {
    }

    private void startReplaceEncryption() {
    }

    private void startXorEncryption() {
    }

    private void startCaesarEncryption() {
    }

    private ScannerInput insertInput() throws FileNotFoundException {
        System.out.println("For FileInput press 1");
        System.out.println("For ScannerInput press 2");
        System.out.println("For StringInput press 3");

        Scanner myObj = new Scanner(System.in);
        Input input;
        switch(myObj.nextInt()) {
            case 1: input = new FileInput();
            case 2: input = new ScannerInput();
            case 3: input = new StringInput();
            default: input = null;
        }

        if(input != null) {
            input.line();
        }
        return null;
    }


}
