package experis.ds.input;

import java.io.FileNotFoundException;

abstract public class Input {
    String data;

    String get(){
        return data;
    }

    public abstract String line() throws FileNotFoundException;
    abstract boolean isEnd();
}
