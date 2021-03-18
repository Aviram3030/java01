package experis.ds;

public class Code {
    private char[] data;
    private int index;
    private final int len;
    private OpCodes op;

    public Code(char [] data){
        this.data = data;
        index = 0;
        len = data.length;
    }

    public OpCodes getOp(){
        assert index < len : "End of the code";

        switch(data[index++]){
            case '+':
                return OpCodes.PLUS;
            case '-':
                return OpCodes.MINUS;
            case '<':
                return OpCodes.LEFT;
            case '>':
                return OpCodes.RIGHT;
            case '!':
                return OpCodes.EXCLAMATION_MARK;
            case '.':
                return OpCodes.DOT;
            case '[':
                return OpCodes.OPEN_BRACKETS;
            case ']':
                return OpCodes.CLOSED_BRACKETS;
            default:
                System.out.println("ERROR,illegal input");
                System.exit(1);
                return OpCodes.DOT;

        }
    }

    public int getIndex(){
        return index;
    }

    public Boolean isFinished(){
        return index == len - 1;
    }

    public int checkIsIllegalBrackets(){
        int balance = 1;
        int start = index;

        while(balance >= 0 && start < len){
            if(data[start] == '['){
                balance++;
            }
            else if(data[start] == ']'){
                balance--;
            }
            start++;

        }

        if(balance == 0){
            return start;
        }

        System.out.println("Error,unclosed brackets");
        System.exit(1);

        return start;

    }



}
