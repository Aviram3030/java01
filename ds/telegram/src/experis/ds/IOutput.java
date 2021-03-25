package experis.ds;

import java.io.IOException;

public interface IOutput {
    void outputTerminal(IEncryption x);
    void outputFile(IEncryption x) throws IOException;
}
