package experis.ds;

public interface IOpCodes {
    int operator(Emulator emulator, Code code, Memory memory, Display display,int index);
}
