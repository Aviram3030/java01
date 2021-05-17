package input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner reader;

    public ConsoleInput(Scanner reader) {
        this.reader = reader;
    }

    @Override
    public String getLine() {
        return reader.nextLine();
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext();
    }
}
