package experis.ds;

import java.io.FileNotFoundException;

public interface OneInputEncryption {
    String stringInput(String txt);
    String fileInput() throws FileNotFoundException;
    String scannerInput();
}
