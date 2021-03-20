package experis.ds;

public class Main {

    public static void main(String[] args) {

        String source=" <<<<<<<<++++++[>++++<-]>\n" +
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

    }

    public static void calculate(String source){
        if(!Emulator.checkIllegalBrackets(source)){
            System.out.println("Error, illegal brackets");
            return;
        }

        OpCodes[] binaryCode = Emulator.turnToOpCode(source);
        Display display = Display.getDisplay();
        Emulator test = new Emulator(display);

        test.load(binaryCode);
        test.execute();

    }


}
