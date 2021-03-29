package experis.ds.output;

public class OutputTerminal implements IOutput{


    @Override
    public void write(String data) {
        System.out.println(data);
    }
}
