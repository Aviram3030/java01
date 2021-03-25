package experis.ds;

public class XorEncryption extends TwoStringEncryption{
    private String longTxt;
    private String shortTxt;

    public XorEncryption(){
    }

    public void load(String a, String b){
        if(compareLength(a.length(), b.length())){
            loadTxt(a, b);
        }
        else{
            loadTxt(b, a);
        }
    }

    private void loadTxt(String longTxt,String shortTxt){
        assert longTxt != null && shortTxt != null : "input is null";
        this.longTxt = longTxt;
        this.shortTxt = shortTxt;
    }

    private Boolean compareLength(int a, int b){
        return a > b;
    }

    public String execute(){
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while(i < longTxt.length()){
            int binaryLongValue = toBinary(longTxt.charAt(i));
            int binaryShortValue = toBinary(shortTxt.charAt(j));

            if(isEqual(binaryLongValue, binaryShortValue))
            {
                sb.append('0');
            }
            else{
                sb.append('1');
            }

            i++;
            j++;

            if(j == shortTxt.length()){
                j = 0;
            }
        }

         return sb.toString();
    }

    private int toBinary(int n){
        return n % 2;
    }

    private boolean isEqual(int a, int b){
        return a == b;
    }


}
