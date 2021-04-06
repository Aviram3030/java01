package experis.ds.userinterface.input;

import java.io.FileNotFoundException;

public interface Input {
    String line() throws FileNotFoundException;
    Boolean hasNext();
}
