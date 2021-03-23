package experis.ds;

public class Transistor {
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
}
