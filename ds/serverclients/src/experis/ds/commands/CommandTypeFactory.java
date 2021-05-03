package experis.ds.commands;

public class CommandTypeFactory {
    public CommandType getType(String msg){
        int pos = msg.indexOf(" ");
        if(pos == -1){
            return CommandType.REGULAR;
        }

        String firstWord = msg.substring(0, pos);
        switch(firstWord){
            case "quit" -> {
                return CommandType.QUIT;
            }
            case "room" -> {
                return CommandType.ENTER_ROOM;
            }
            case "leave" -> {
                return CommandType.LEAVE_ROOM;
            }
            case "list" -> {
                return CommandType.ROOMS_LIST;
            }
            case "nick" -> {
                return CommandType.NICK;
            }
            case "msg" -> {
                return CommandType.MESSAGE_USER;
            }
            default -> {
                return CommandType.REGULAR;
            }
        }
    }
}
