package experis.ds.database.creators;

public class Creators implements ICreators {
    private final String name;

    public Creators(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
