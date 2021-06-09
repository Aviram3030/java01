package usercommands;

import factory.IRobotFactory;
import input.Input;
import output.Output;
import robotcommands.RobotsGroup;
import usercommands.*;

import java.util.HashMap;

public class UserCommandsService {
    private final HashMap<UserCommandType, IUserCommand> userCommands = new HashMap<>();
    private final UserCommandsParser parser = new UserCommandsParser();

    public UserCommandsService(Input input, Output output, IRobotFactory robotFactory) {
        var robots = new RobotsGroup(robotFactory);
        initialize(robots, input, output);
    }

    private void initialize(RobotsGroup robots, Input input, Output output) {
        userCommands.put(UserCommandType.ROBOT_COMMAND, new IssueRobotCommands(robots, input, output));
        userCommands.put(UserCommandType.PROVISION, new ProvisionRobot(robots, input, output));
    }

    public void execute(String txt) {
        var type = parser.getCommand(txt);
        userCommands.get(type).start();
    }
}
