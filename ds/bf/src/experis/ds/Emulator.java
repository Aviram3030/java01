package experis.ds;

public class Emulator {
    private Memory memory;
    private Code code;

    public Emulator(Memory memory, Code code){
        this.memory = memory;
        this.code = code;
    }

    public void execute(int start, int end){
        int closedBracket;

        while(!code.isFinished()){
            switch(code.getOp()){
                case PLUS:
                    memory.increase();
                    break;
                case MINUS:
                    memory.decrease();
                    break;
                case LEFT:
                    memory.moveLeft();
                    break;
                case RIGHT:
                    memory.moveRight();
                    break;
                case DOT:
                    memory.printChar();
                    break;
                case EXCLAMATION_MARK:
                    memory.printValue();
                    break;
                case OPEN_BRACKETS:
                    closedBracket = code.checkIsIllegalBrackets();
                    while(memory.getValue() != 0){
                        int openBracket = code.getIndex();
                        execute(openBracket, closedBracket - 1);
                    }
                    break;
                case CLOSED_BRACKETS:
                    System.out.println("ERROR,illegal input");
                    System.exit(1);


            }
        }

    }




}
