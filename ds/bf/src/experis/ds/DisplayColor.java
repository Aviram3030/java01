package experis.ds;

public class DisplayColor implements IDisplay{

    public void printChar(int n){
            System.out.print("\033[34;1;4m");
            System.out.print((char)n);
            System.out.print("\033[0m");
    }

    public void printValue(int n){
            System.out.print("\033[34;1;4m");
            System.out.print(n);
            System.out.print("\033[0m");
    }
}
