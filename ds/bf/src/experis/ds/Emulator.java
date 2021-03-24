package experis.ds;

public class Emulator {
    private final Memory memory;
    private final IDisplay display;
    private Code code;

    public Emulator(IDisplay display){
        memory = new Memory();
        this.display = display;
    }

    public void load(OpCodes[] code){
        this.code = new Code(code);
    }

    public void execute(){
        run(0, code.getSize());
    }

    public void run(int start, int end){

        while(start < end){
            start = code.getOp().operator(this, code, memory, display, start);
            /*switch (code.getOp()) {
                case PLUS -> memory.write(memory.read() + 1);
                case MINUS -> memory.write(memory.read() - 1);
                case LEFT -> memory.moveLeft();
                case RIGHT -> memory.moveRight();
                case DOT -> display.printChar(memory.read());
                case EXCLAMATION_MARK -> display.printValue(memory.read());
                case BEGIN_LOOP -> start = startLoop();
                case BEGIN_IF -> start = startIf();
            }*/
            start++;
        }

    }
/*
    private int startIf(){
        int closedBracket = code.getEndIf();
        int openBracket = code.getIndex();

        if(memory.read() != 0){
            code.setIndex(openBracket);
            run(openBracket, closedBracket - 1);
        }

        code.setIndex(closedBracket);
        return closedBracket - 1;
    }

    private int startLoop(){
        int closedBracket = code.getClosedBracket();
        int openBracket = code.getIndex();

        while(memory.read() != 0){
            code.setIndex(openBracket);
            run(openBracket, closedBracket - 1);
        }

        code.setIndex(closedBracket);
        return closedBracket - 1;
    }



    public static String fixing(String s){
        StringBuilder sb = new StringBuilder(s);

        int index =0;
        while (index < sb.length()) {
            switch (sb.charAt(index)) {
                case '+', '-', '<', '>', '!', '.', '[', ']', ',',')','(' -> index++;
                default -> sb.deleteCharAt(index);
            }
        }

        return sb.toString();
    }

    public static OpCodes[] turnToOpCode(String s){
        OpCodes[] opCodes = new OpCodes[s.length()];

        for(int i = 0; i < opCodes.length; i++){
            opCodes[i] = getOp(s.charAt(i));
        }

        return opCodes;
    }
    private static OpCodes getOp(char c){
        return switch (c) {
            case '+' -> OpCodes.PLUS;
            case '-' -> OpCodes.MINUS;
            case '<' -> OpCodes.LEFT;
            case '>' -> OpCodes.RIGHT;
            case '!' -> OpCodes.EXCLAMATION_MARK;
            case '.' -> OpCodes.DOT;
            case '[' -> OpCodes.BEGIN_LOOP;
            case ']' -> OpCodes.END_LOOP;
            case '(' -> OpCodes.BEGIN_IF;
            case ')' -> OpCodes.END_IF;
            default -> OpCodes.COMMA;
        };
    }


    public static Boolean checkIllegalBrackets(String s){
        int balance = 0;

        for(int i = 0; i < s.length() && balance >= 0; i++){
            if(s.charAt(i) == '['){
                balance++;
            }
            else if(s.charAt(i) == ']'){
                balance--;
            }
        }

        return balance == 0;

    }

    public static Boolean checkIllegalIf(String s){
        int balance = 0;

        for(int i = 0; i < s.length() && balance >= 0; i++){
            if(s.charAt(i) == '('){
                balance++;
            }
            else if(s.charAt(i) == ')'){
                balance--;
            }
        }

        return balance == 0;

    }


*/

}
