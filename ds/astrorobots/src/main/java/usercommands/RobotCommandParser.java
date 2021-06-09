package usercommands;

import robotcommands.*;

import java.util.HashMap;

public class RobotCommandParser {
    private final HashMap<String, RobotCommandType> robotCommands = new HashMap<>();
    public RobotCommandParser(){
        robotCommands.put("1", RobotCommandType.DISPATCH);
        robotCommands.put("2", RobotCommandType.DELETE);
        robotCommands.put("3", RobotCommandType.REBOOT);
        robotCommands.put("4", RobotCommandType.SELF_DIAGNOSTICS);
    }

    public RobotCommandType getCommand(String txt){
        return robotCommands.get(txt);
    }
}
