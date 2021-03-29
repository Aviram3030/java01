package experis.ds.encryption;

public class CaesarEncryption extends Encryption {
    private final int lowAsciiValue = 65;
    private final int highAsciiValue = 97;
    private final int numOfAlphaBet = 26;
    private int val;

    public CaesarEncryption(String txt) {
        this.txt = txt;
    }

    public void load(int val) {
        assert txt != null : "input is null";
        this.val = val;
    }

    public String execute() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            if (Character.isAlphabetic(txt.charAt(i)) && Character.isUpperCase(txt.charAt(i))) {
                char ch = (char) (((int) txt.charAt(i) + val - lowAsciiValue) % numOfAlphaBet + lowAsciiValue);
                sb.append(ch);
            } else {
                char ch = (char) (((int) txt.charAt(i) + val - highAsciiValue) % numOfAlphaBet + highAsciiValue);
                sb.append(ch);
            }
        }

        txt = sb.toString();
        return txt;
    }



}
