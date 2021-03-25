package experis.ds.input;

public class StringInput extends Input{

    public void readString(String... txt) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < txt.length; i++){
            sb.append(txt[i]);
            sb.append("\n");
        }

        data = sb.toString();
    }

    @Override
    public String line() {
        return null;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
