package output;

public class ConsoleDisplay implements Display {
    @Override
    public <T> void print(T obj) {
        System.out.println(obj.toString());
    }
}
