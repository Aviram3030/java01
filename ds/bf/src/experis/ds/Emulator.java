package experis.ds;

public class Emulator {
    private final Memory memory;
    private final Display display;
    private Code code;

    public Emulator(Display display){
        memory = new Memory();
        this.display = display;
    }

    public void load(OpCodes[] code){
        this.code = new Code(code);
    }

    public void execute(){
        run(0, code.getSize());
    }

    private void run(int start, int end){

        while(start < end){
            switch (code.getOp()) {
                case PLUS -> memory.write(memory.read() + 1);
                case MINUS -> memory.write(memory.read() - 1);
                case LEFT -> memory.moveLeft();
                case RIGHT -> memory.moveRight();
                case DOT -> display.printChar(memory);
                case EXCLAMATION_MARK -> display.printValue(memory);
                case OPEN_BRACKETS -> start = brackets();
            }
            start++;
        }

    }

    private int brackets(){
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
                case '+', '-', '<', '>', '!', '.', '[', ']', ',' -> index++;
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
            case '[' -> OpCodes.OPEN_BRACKETS;
            case ']' -> OpCodes.CLOSED_BRACKETS;
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




}
