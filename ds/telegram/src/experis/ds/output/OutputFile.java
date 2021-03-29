package experis.ds.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile implements IOutput{

    private BufferedWriter writer;

    public OutputFile(String fileName) throws IOException {
        assert fileName != null: "Error, file name is null";

        writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void write(String data) throws IOException {
        writer.write(data);
        writer.close();
    }
}
