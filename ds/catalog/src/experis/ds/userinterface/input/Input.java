package experis.ds.userinterface.input;

import java.io.FileNotFoundException;

public interface Input {
    public String line() throws FileNotFoundException;
}