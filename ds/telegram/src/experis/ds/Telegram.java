package experis.ds;

import experis.ds.encryption.*;
import experis.ds.input.InputFile;
import experis.ds.input.Input;
import experis.ds.input.ScannerInput;
import experis.ds.input.StringInput;
import experis.ds.output.IOutput;
import experis.ds.output.OutputFile;
import experis.ds.output.OutputTerminal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Telegram {

    private CaesarEncryption caesar;
    private ReplaceEncryption replace;
    private UpperCase upper;
    private LowerCase lower;
    private XorEncryption xor;

    private Scanner myObj = new Scanner(System.in);
    private Encryption manipulation;
    String data;
    String goAgain = "";


    public void run() throws IOException {
        System.out.println("Hello, which string manipulation would you like to apply?");
        printInterFace();

        String s = myObj.nextLine();

        while(!s.equals(".")){
            manipulation = getData(s, data);
            if(manipulation == null){
                return;
            }

            manipulation.execute();
            data = manipulation.getTxt();

            if(data != null) {
                getOutput(data);
            }

            keepManipulation(s);

            data = "";
            printInterFace();
            s = myObj.nextLine();

        }
    }

    private void printInterFace(){
        System.out.println("For uppercase - enter 1");
        System.out.println("For lowerCase - enter 2");
        System.out.println("For replaceEncryption - enter 3");
        System.out.println("For xorEncryption - enter 4");
        System.out.println("For caesarEncryption? - enter 5");
        System.out.println("To exit - enter 6");

    }

    private void keepManipulation(String s) throws IOException {
        while(true){
            System.out.println("Do you want to keep manipulation on current data?");
            System.out.println("press 1 for yes");
            System.out.println("press any key for no");

            goAgain = myObj.nextLine();
            if(goAgain.equals("1")){
                printInterFace();
                s = myObj.nextLine();
                manipulation = getData(s, data);
                if(manipulation == null){
                    return;
                }

                manipulation.execute();
                data = manipulation.getTxt();
                if(data != null) {
                    getOutput(data);
                }
            }
            else {
                break;
            }

        }
    }

    private Encryption getData(String s, String data) throws FileNotFoundException {
        switch(s.toLowerCase()){
            case "1":
                return makeUpper(data);
            case "2":
                return makeLower(data);
            case "3":
                return makeReplace(data);
            case "4":
                return makeXor(data);
            case "5":
                return makeCaesar(data);
            case "6": return null;
            default:
                throw new IllegalStateException("Unexpected value: " + s.toLowerCase());
        }

    }

    private UpperCase makeUpper(String data) throws FileNotFoundException {
        if(upper != null && goAgain.equals("1")) {
            upper.setTxt(data);
        }
        else if(goAgain.equals("1")){
            upper = new UpperCase(data);
        }
        else{
            upper = new UpperCase(insertInput());
        }

        return upper;
    }

    private LowerCase makeLower(String data) throws FileNotFoundException {
        if(lower != null && goAgain.equals("1")) {
            lower.setTxt(data);
        }
        else if(goAgain.equals("1")){
            lower = new LowerCase(data);
        }
        else{
            lower = new LowerCase(insertInput());
        }

        return lower;
    }

    private ReplaceEncryption makeReplace(String data) throws FileNotFoundException {
        if(replace != null && goAgain.equals("1")) {
            replace.setTxt(data);
        }
        else if(goAgain.equals("1")){
            replace = new ReplaceEncryption(data);
        }
        else{
            replace = new ReplaceEncryption(insertInput());
        }

        return replace;
    }

    private XorEncryption makeXor(String data) throws FileNotFoundException {
        if(xor != null && goAgain.equals("1")) {
            xor.setTxt(data);
        }
        else if(goAgain.equals("1")){
            xor = new XorEncryption(data);
        }
        else{
            xor = new XorEncryption(insertInput());
        }

        System.out.print("For password, ");
        xor.load(insertInput());
        return xor;
    }

    private CaesarEncryption makeCaesar(String data) throws FileNotFoundException {
        if(caesar != null && goAgain.equals("1")) {
            caesar.setTxt(data);
        }
        else if(goAgain.equals("1")){
            caesar = new CaesarEncryption(data);
        }
        else{
            caesar = new CaesarEncryption(insertInput());
        }

        System.out.print("For value, ");
        caesar.load(Integer.parseInt(insertInput()));
        return caesar;
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

        IOutput outPut;

        Scanner myObj = new Scanner(System.in);
        switch(myObj.nextInt()) {
            case 1:
                outPut = new OutputFile("output.txt");
                break;
            case 2:
                outPut = new OutputTerminal();
                break;
            default: return;
        }
        outPut.write(s);
    }


}
