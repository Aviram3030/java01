package experis.ds;

public class MemoryTest {
    public static void fx1(){
        String source=" [<!>][<!>][<!>]\n" +
                "        ++++++++[>++++>++++++>++++++++>++++++++++>++++++++++++<<<<<-]>\n" +
                "        >>>------.>+.+++++++++++++++++++++.---------------------.<<<<.>>>>++++++\n" +
                "        +++++++++++++.------------.---.<<<<.>>>--.>++++++++++++++++.-.<<<<+.\n" +
                "        [>]<[[-]<]";
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
        calculate(source);
        System.out.println();
    }

    public static void fx3(){
        String source =" ++++++++[>++++>++++++>++++++++>++++++++++>++++++++++++<<<<<-]>>>+++++++.>>+.++++++++++++.--------.<<<<.>>>-.>+++++++++++++++++.-----------------.+++++++++++++.<<<+.";
        calculate(source);
        System.out.println();
    }

    public static void fx4(){
        String source=">>>>-+<<<<<<<<<<++++++++++++++++++++++>+<++++++++++>>---<<+++++++++++++++++++><><.--<>---.+++.+++.---.++++.";
        calculate(source);
        System.out.println();

    }

    public static void calculate(String source){
        source = Transpiler.fixing(source);

        if(!Transpiler.checkIllegalBrackets(source)){
            System.out.print("Error, illegal while brackets");
            return;
        }
        if(!Transpiler.checkIllegalIf(source)){
            System.out.print("Error,illegal if brackets");
            return;
        }

        OpCodes[] binaryCode = Transpiler.turnToOpCode(source);
        IDisplay display = new DisplayColor();
        Emulator test = new Emulator(display);

        test.load(binaryCode);
        test.execute();

    }
}
