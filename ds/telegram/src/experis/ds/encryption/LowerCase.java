package experis.ds.encryption;

public class LowerCase extends OneStringEncryption{

    public LowerCase(String txt){
        assert txt != null : "input is null";
        this.txt = txt;
    }

    public String execute(){
        return txt.toLowerCase();
    }
}
