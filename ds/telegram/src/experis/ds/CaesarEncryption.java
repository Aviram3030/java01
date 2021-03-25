package experis.ds;

public class CaesarEncryption {
    private String txt;
    private int val;

    public CaesarEncryption(){
    }

    public void load(String txt, int val){
        assert txt != null : "input is null";

        this.txt = txt;
        this.val = val;
    }

    public String execute(){
    StringBuilder sb = new StringBuilder();
        for(int i = 0;i < txt.length(); i++){
            if(checkOverFlow(i)){
                sb.append(0);
            }
            else{
                sb.append(txt.charAt(i) + val);
            }
        }
        return txt;
    }

    private boolean checkOverFlow(int index){
        long sum = (long)txt.charAt(index) + (long)val;
        return sum > Integer.MAX_VALUE;
    }

}
