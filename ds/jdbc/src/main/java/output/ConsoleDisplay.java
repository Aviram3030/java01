package output;

import entity.Model;

public class ConsoleDisplay implements Display {
    @Override
    public void displayModel(Model obj) {
        System.out.println(obj.toString());
    }

    @Override
    public void printMessage(String txt) {
        System.out.println(txt);
    }


}
