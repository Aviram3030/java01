package experis.ds.encryption;

public class XorEncryption extends Encryption {
    private String password;

    public XorEncryption(String txt){
        this.txt = txt;
    }

    public void load(String password) {
        this.password = password;
    }

    public String execute(){
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while(i < txt.length()){
            sb.append(txt.charAt(i) ^ password.charAt(j));

            ++i;
            ++j;
            if(j == password.length()){
                j = 0;
            }
        }

         txt = sb.toString();
         return sb.toString();
    }


}
