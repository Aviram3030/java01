package experis.ds.logic.Query;

public class Command {
    private final String title;
    private final CommandType type;

    public Command(String title, CommandType type){
        this.title = title;
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    public String getTitle(){
        return title;
    }

}
