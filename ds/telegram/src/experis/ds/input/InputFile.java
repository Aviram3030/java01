package experis.ds.input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputFile extends Input{

    private File myObj = new File("filename.txt");
    Scanner myReader = new Scanner(myObj);

    public InputFile() throws FileNotFoundException {
    }

    public void readFile() throws FileNotFoundException {
        Path path = Paths.get("input.txt");
        StringBuilder sb = new StringBuilder();

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(s -> sb.append(s).append("\n"));

        } catch (IOException ex) {
            System.out.println("Unable to read the file");
        }

        data = sb.toString();
    }

    @Override
    public String line() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        while(!isEnd()){
            sb.append(myReader.nextLine());
        }

        return sb.toString();
    }

    @Override
    public boolean isEnd() {
        return myReader.hasNext();
    }
}
