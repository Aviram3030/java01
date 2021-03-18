package experis.ds;

public class Main {

    public static void main(String[] args) {

        Memory memory = new Memory(100, 10);
        char[] c = {'!','.','+','+','-','<','!','>','!'};
        Code code = new Code(c);

        Emulator test = new Emulator(memory, code);
        test.execute();


    }
}
