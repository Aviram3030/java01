package experis.ds.database.fields;

public class ISBN {
    private String text;

    public ISBN(String text) throws Exception {
        if(text.length() != 9 && text.length() != 10 && text.length() != 13){
            throw new Exception();
        }
        if(text.length() == 9){
            StringBuilder sb = new StringBuilder();
            sb.append("0");
            sb.append(text);
            text = sb.toString();
        }

        this.text = text;
    }

    public String getText(){
        return text;
    }
}
