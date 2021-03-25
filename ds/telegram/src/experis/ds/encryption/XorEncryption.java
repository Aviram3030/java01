package experis.ds.encryption;

public class XorEncryption extends OneStringEncryption{
    private String password;

    public XorEncryption() {
    }

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
            int binaryLongValue = toBinary(txt.charAt(i));
            int binaryShortValue = toBinary(password.charAt(j));

            if(isEqual(binaryLongValue, binaryShortValue))
            {
                sb.append('0');
            }
            else{
                sb.append('1');
            }

            i++;
            j++;

            if(j == password.length()){
                j = 0;
            }
        }

         txt = sb.toString();
         return sb.toString();
    }

    private int toBinary(int n){
        return n % 2;
    }

    private boolean isEqual(int a, int b){
        return a == b;
    }


}
