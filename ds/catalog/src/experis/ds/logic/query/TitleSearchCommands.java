package experis.ds.logic.query;

import experis.ds.logic.query.Command;
import experis.ds.logic.query.CommandType;

import java.util.ArrayList;

public class TitleSearchCommands {
    private ArrayList<Command> commands = new ArrayList<>();

    public void add(String title){
        commands.add(getCommand(title));
    }

    public Boolean search(String bookTitle){
        for(Command command : commands){
            if(!checkIfContains(command, bookTitle)){
                return false;
            }
        }
        return true;
    }

    private Command getCommand(String title){
        if(title.charAt(0) == '-'){
            return new Command(title.substring(1), CommandType.NOT_PRESENT);
        }

        return new Command(title,CommandType.PRESENT);
    }

    private Boolean checkIfContains(Command command, String bookTitle){
        String title = command.getTitle().toLowerCase();
        CommandType type = command.getType();
        bookTitle = bookTitle.toLowerCase();

        if(type == CommandType.NOT_PRESENT && bookTitle.contains(title)) {
            return false;
        }
        if(type == CommandType.PRESENT && !bookTitle.contains(title)){
            return false;
        }
        return true;
    }
}

