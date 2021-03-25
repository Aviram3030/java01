package experis.ds;

import java.io.FileNotFoundException;

abstract public class TwoStringEncryption implements IEncryption{
    String txt;
    abstract void load(String a, String b);


}
