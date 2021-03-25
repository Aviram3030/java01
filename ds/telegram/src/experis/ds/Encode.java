package experis.ds;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Encode implements OneInputEncryption{
    @Override
    public String stringInput(String txt) {
        return txt;
    }

    @Override
    public String fileInput() throws FileNotFoundException {
        Path path = Paths.get("input.txt");
        StringBuilder sb = new StringBuilder();

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(s -> sb.append(s).append("\n"));

        } catch (IOException ex) {
            System.out.println("Unable to read the file");
        }

        return sb.toString();
    }

    @Override
    public String scannerInput() {
        Scanner myObj = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        sb.append(myObj.nextLine());

        return sb.toString();
    }

}
