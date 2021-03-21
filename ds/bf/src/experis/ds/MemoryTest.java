package experis.ds;

public class MemoryTest {
    public static void fx1(){
        String source=" [<!>][<!>][<!>]\n" +
                "        ++++++++[>++++>++++++>++++++++>++++++++++>++++++++++++<<<<<-]>\n" +
                "        >>>------.>+.+++++++++++++++++++++.---------------------.<<<<.>>>>++++++\n" +
                "        +++++++++++++.------------.---.<<<<.>>>--.>++++++++++++++++.-.<<<<+.\n" +
                "        [>]<[[-]<]";
        source = Emulator.fixing(source);
        calculate(source);
        System.out.println();
    }

    public static void fx2(){
        String source ="  ++++++[>++++<-]>\n" +
                "         >+++++++++<\n" +
                "        [\n" +
                "             >>>+<<\n" +
                "             [>+>[-]<<-]\n" +
                "              >[<+>-]\n" +
                "             >[<<++++++++++>>>+<-]\n" +
                "             <<-\n" +
                "             <-\n" +
                "       ]\n" +
                "       >>>>[<<<<+>>>>-]<<<<\n" +
                "       >[-]<\n" +
                "       !";
        source = Emulator.fixing(source);
        calculate(source);
        System.out.println();
    }

    public static void fx3(){
        String source =" ++++++++[>++++>++++++>++++++++>++++++++++>++++++++++++<<<<<-]>>>+++++++.>>+.++++++++++++.--------.<<<<.>>>-.>+++++++++++++++++.-----------------.+++++++++++++.<<<+.";
        source = Emulator.fixing(source);
        calculate(source);
        System.out.println();
    }

    public static void fx4(){
        String source=">>>>-+<<<<<<<<<<++++++++++++++++++++++>+<++++++++++>>---<<+++++++++++++++++++><><.--<>---.+++.+++.---.++++.";
        source = Emulator.fixing(source);
        calculate(source);
        System.out.println();

    }

    public static void calculate(String source){
        if(!Emulator.checkIllegalBrackets(source)){
            System.out.print("Error, illegal brackets");
            return;
        }

        OpCodes[] binaryCode = Emulator.turnToOpCode(source);
        Display display = Display.getDisplay();
        Emulator test = new Emulator(display);

        test.load(binaryCode);
        test.execute();

    }
}
