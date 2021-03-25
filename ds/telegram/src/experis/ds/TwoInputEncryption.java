package experis.ds;

import java.io.FileNotFoundException;

public interface TwoInputEncryption {
    String stringInput(String txt);
    String fileInput() throws FileNotFoundException;
    String scannerInput();
}
