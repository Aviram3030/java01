package experis.ds.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class outputFile {

    private BufferedWriter writer;

    public outputFile(String fileName) throws IOException {
        assert fileName != null: "Error, file name is null";

        writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void write(String data) throws IOException {
        writer.write(data);
        writer.close();
    }
}
