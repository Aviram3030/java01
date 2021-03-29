package experis.ds.encryption;

public class ReplaceEncryption extends Encryption {

    public ReplaceEncryption(String txt){
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

        txt = sb.toString();
        return txt;
    }
}
