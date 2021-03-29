package experis.ds.encryption;

public class LowerCase extends Encryption {


    public LowerCase(String txt){
        assert txt != null : "input is null";
        this.txt = txt;
    }

    public String execute(){
        txt = txt.toLowerCase();
        return txt;
    }
}
