package experis.ds;

public class Emulator {
    private Memory memory;
    private Code code;

    public Emulator(Memory memory, Code code){
        this.memory = memory;
        this.code = code;
    }

    public void execute(){
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
                /* case OPEN_BRACKETS:
                    memory.loop();
                    break;
                case CLOSED_BRACKETS:
                    System.out.println("ERROR,illegal input");
                    System.exit(1); */


            }
        }


    }


}
