package experis.ds;

public class Display {

    private static Display display = null;

    private Display(){
    }

    public static Display getDisplay(){
        if(display == null){
            Display.display = new Display();
        }

        return display;
    }

    public void printChar(Memory memory){
        System.out.print((char)memory.read());
    }

    public void printValue(Memory memory){
        System.out.print(memory.read());
    }

}
