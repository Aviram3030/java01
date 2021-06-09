package usercommands;

import usercommands.UserCommandType;

import java.util.HashMap;

public class UserCommandsParser {
    private final HashMap<String, UserCommandType> commands = new HashMap<>();

    public UserCommandsParser(){
        commands.put("1", UserCommandType.PROVISION);
        commands.put("2", UserCommandType.ROBOT_COMMAND);
    }

    public UserCommandType getCommand(String txt){
        return UserCommandType.valueOf(txt.toUpperCase());
    }
}
