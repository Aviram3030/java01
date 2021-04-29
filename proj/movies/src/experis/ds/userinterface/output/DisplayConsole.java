package experis.ds.userinterface.output;

public class DisplayConsole<T> implements Display {
    @Override
    public void getOutput(Object data) {
        System.out.println(data.toString());
    }
}
