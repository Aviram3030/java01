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

    public void printChar(int n){
        System.out.print((char) n);
    }

    public void printValue(int n){
        System.out.print(n +" ");
    }

}
