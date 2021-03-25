package experis.ds;

public class ReplaceEncryption extends OneStringEncryption {

    public ReplaceEncryption(){
    }

    public void load(String txt){
        assert txt != null : "input is null";
        this.txt = txt;
    }

    public String execute(){
        StringBuilder sb = new StringBuilder(txt);

        return replaceLetters(sb);
    }

    private String replaceLetters(StringBuilder sb){
        for(int i = 0; i < txt.length(); i++){
            switch (sb.toString().charAt(i)){
                case 'a','i','e','o','u','A','I','E','O','U' -> sb.setCharAt(i , '#');
            }
        }

        return sb.toString();
    }
}
