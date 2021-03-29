package experis.ds.encryption;

public class UpperCase extends Encryption {

    public UpperCase(String txt){
        assert txt != null : "input is null";
        this.txt = txt;
    }

    public String execute(){
        txt = txt.toUpperCase();
        return txt;
    }
}
