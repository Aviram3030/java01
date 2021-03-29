package experis.ds.encryption;

 abstract public class Encryption implements IEncryption{
     String txt;

     public void setTxt(String s){
         txt = s;
     }

     public String getTxt(){
         return txt;
     }
}
