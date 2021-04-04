package experis.ds.persons;

public class Person implements IPerson {
    private final String name;

    public Person(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
