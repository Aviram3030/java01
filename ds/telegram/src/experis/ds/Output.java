package experis.ds;

import java.io.FileWriter;
import java.io.IOException;

public class Output implements IOutput{

    @Override
    public void outputTerminal(IEncryption x){
        System.out.println(x.execute());
    }

    @Override
    public void outputFile(IEncryption x) throws IOException {
        FileWriter myWriter = new FileWriter("input.txt");
        myWriter.write(x.execute());
        myWriter.close();
    }
}
