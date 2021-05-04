package experis.ds.commands;

import java.util.concurrent.ConcurrentHashMap;

public class CommandTypeFactory {
    private final ConcurrentHashMap<String,CommandType> commandTypes = new ConcurrentHashMap<>();

    public CommandTypeFactory(){
        commandTypes.put("quit",CommandType.QUIT);
        commandTypes.put("room",CommandType.ENTER_ROOM);
        commandTypes.put("leave",CommandType.LEAVE_ROOM);
        commandTypes.put("list",CommandType.ROOMS_LIST);
        commandTypes.put("nick",CommandType.NICK);
        commandTypes.put("msg",CommandType.MESSAGE_USER);
    }

    public CommandType getType(String msg){
        String firstWord = getFirstWord(msg);
        CommandType type = commandTypes.get(firstWord);
        if(type == null){
            return CommandType.REGULAR_MESSAGE;
        }
        return type;
    }

    public String getFirstWord(String msg){
        int pos = msg.indexOf(" ");
        if(pos == -1){
            pos = msg.length();
        }
        return msg.substring(0, pos);
    }

}
