package experis.ds;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTests {
    public static void TerminalOutput() throws FileNotFoundException {
        Output output = new Output();
        Encode x = new Encode();
        String s = x.scannerInput();

        ReplaceEncryption test = new ReplaceEncryption();
        test.load(s);
        output.outputTerminal(test);

        UpperCase test2 = new UpperCase();
        test2.load(s);
        output.outputTerminal(test2);

        LowerCase test3 = new LowerCase();
        test3.load(s);
        output.outputTerminal(test3);

    }

    public static void FileOutput() throws IOException {
        Output output = new Output();
        Encode x = new Encode();
        String s = x.scannerInput();

        ReplaceEncryption test = new ReplaceEncryption();
        test.load(s);
        output.outputFile(test);

    }

    public static void FileOutput2() throws IOException {
        Output output = new Output();
        Encode x = new Encode();
        String s = x.scannerInput();

        UpperCase test2 = new UpperCase();
        test2.load(s);
        output.outputFile(test2);
    }

    public static void FileOutput3() throws IOException {
        Output output = new Output();
        Encode x = new Encode();
        String s = x.scannerInput();

        LowerCase test3 = new LowerCase();
        test3.load(s);
        output.outputFile(test3);
    }

    public static void FileInputTerminalOutput() throws IOException {
        Output output = new Output();
        Encode x = new Encode();
        String s = x.fileInput();

        LowerCase test3 = new LowerCase();
        test3.load(s);
        output.outputTerminal(test3);
    }

    public static void FileInputTerminalOutput2() throws IOException {
        Output output = new Output();
        Encode x = new Encode();
        String s = x.fileInput();

        ReplaceEncryption test3 = new ReplaceEncryption();
        test3.load(s);
        output.outputTerminal(test3);
    }


    public static void FileInputTerminalOutput3() throws FileNotFoundException {
        Output output = new Output();
        Encode x = new Encode();
        String s = x.fileInput();

        UpperCase test3 = new UpperCase();
        test3.load(s);
        output.outputTerminal(test3);
    }

    public static void TerminalOutput2() throws FileNotFoundException {
        Output output = new Output();
        EncodeTwo x = new EncodeTwo();
        String s = x.scannerInput();
        String[] two = x.splitToTwoStrings(s);

        XorEncryption test3 = new XorEncryption();
        test3.load(two[0],two[1]);
        output.outputTerminal(test3);
    }
}
