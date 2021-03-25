package experis.ds.encryption;

public class UpperCase extends OneStringEncryption {

    public UpperCase(){
    }

    public void load(String txt){
        assert txt != null : "input is null";
        this.txt = txt;
    }

    public String execute(){
        return txt.toUpperCase();
    }
}
