package experis.ds;

public enum OpCodes implements IOpCodes{
    PLUS{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            memory.write(memory.read() + 1);
            return index;
        }
    },
    MINUS {
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            memory.write(memory.read() - 1);
            return index;
        }
    },
    LEFT{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            memory.moveLeft();
            return index;
        }
    },
    RIGHT{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            memory.moveRight();
            return index;
        }

    },
    EXCLAMATION_MARK{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            display.printValue(memory.getCurrentValue());
            return index;
        }
    },
    DOT{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            display.printChar(memory.getCurrentValue());
            return index;
        }
    },
    BEGIN_LOOP{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
                int openBracket = code.getIndex();
                int closedBracket = code.getClosedBracket();

                while(memory.read() != 0){
                    code.setIndex(openBracket);
                    emulator.run(openBracket, closedBracket - 1);
                }

                code.setIndex(closedBracket);
                return closedBracket - 1;

        }
    },
    END_LOOP{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            return index;
        }
    },
    BEGIN_IF{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            int openBracket = code.getIndex();
            int closedBracket = code.getEndIf() + 1;

            if(memory.read() != 0){
                emulator.run(openBracket, closedBracket - 1);
            }

            code.setIndex(closedBracket);
            return closedBracket - 1;
        }
    },
    END_IF{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            return index;
        }
    },
    COMMA{
        public int operator(Emulator emulator, Code code, Memory memory, IDisplay display,int index){
            return index;
        }
    };

}