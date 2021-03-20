package experis.ds;

public class Code {
    private int index = 0;
    private final int len;
    private final OpCodes[] op;

    public Code(OpCodes[] op){
        this.op = op;
        len = op.length;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getSize(){
        return len;
    }

    public OpCodes getOp(){
        return op[index++];
    }

    public int getClosedBracket(){
        int balance = 1;
        int start = index;

        while(balance > 0 && start < len){
            if(op[start] == OpCodes.OPEN_BRACKETS){
                balance++;
            }
            else if(op[start] == OpCodes.CLOSED_BRACKETS){
                balance--;
            }

            start++;
        }

        return start;
    }



}
