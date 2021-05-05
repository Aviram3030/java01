package experis.ds.commands;

import java.util.concurrent.ConcurrentHashMap;

public class CommandTypeFactory {
    private final ConcurrentHashMap<String,CommandType> oneWordCommandTypes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String,CommandType> commandTypes = new ConcurrentHashMap<>();

    public CommandTypeFactory(){
        fillCommandType();
        fillOneWordCommandType();
    }

    private void fillCommandType(){
        commandTypes.put("nick",CommandType.NICK);
        commandTypes.put("msg",CommandType.MESSAGE_USER);
        commandTypes.put("room",CommandType.ENTER_ROOM);
    }

    private void fillOneWordCommandType(){
        oneWordCommandTypes.put("quit",CommandType.QUIT);
        oneWordCommandTypes.put("leave",CommandType.LEAVE_ROOM);
        oneWordCommandTypes.put("list",CommandType.ROOMS_LIST);
    }

    public CommandType getType(String msg){
        String firstWord = getFirstWord(msg);
        CommandType type;
        if(is_word(msg)){
            type = oneWordCommandTypes.get(firstWord);
        }
        else {
            type = commandTypes.get(firstWord);
        }
        if(type == null){
            return CommandType.REGULAR_MESSAGE;
        }
        return type;
    }

    private String getFirstWord(String msg){
        int pos = msg.indexOf(" ");
        if(pos == -1){
            pos = msg.length();
        }
        return msg.substring(0, pos);
    }

    private boolean is_word(String msg) {
        return (msg.length() > 0 && msg.split("\\s+").length == 1);
    }

}
