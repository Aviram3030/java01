package experis.ds;

import java.io.FileWriter;
import java.io.IOException;

 abstract public class OneStringEncryption implements IEncryption{
     String txt;
     abstract void load(String txt);
}
