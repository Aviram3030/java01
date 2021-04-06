package experis.ds.database.fields;

public class ISBN {
    private String text;

    public ISBN(String text) throws Exception {
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
