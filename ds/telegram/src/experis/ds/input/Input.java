package experis.ds.input;

import java.io.FileNotFoundException;

abstract public class Input {
    String data;

    public abstract String line() throws FileNotFoundException;

    public abstract boolean isEnd();

    protected String deleteLastLine(StringBuilder sb){
        int last = sb.lastIndexOf("\n");
        if(last == -1){
            return "";
        }

        return sb.substring(0, last);
    }
}
