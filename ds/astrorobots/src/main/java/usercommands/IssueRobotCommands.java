package usercommands;

import input.Input;
import output.Output;
import robotcommands.*;

import java.util.HashMap;

public class IssueRobotCommands implements IUserCommand {
    private final HashMap<RobotCommandType, IRobotCommand> robotCommands = new HashMap<>();
    private final RobotCommandParser parser = new RobotCommandParser();
    private final RobotsGroup robots;
    private final Input input;
    private final Output output;

    public IssueRobotCommands(RobotsGroup robots, Input input, Output output) {
        this.robots = robots;
        this.input = input;
        this.output = output;
        initialize(robots);
    }

    //TODO: fix null
    private void initialize(RobotsGroup robots) {
        robotCommands.put(RobotCommandType.DISPATCH, new DispatchCommand(robots));
        robotCommands.put(RobotCommandType.DELETE, new DeleteCommand(robots));
        robotCommands.put(RobotCommandType.REBOOT, new RebootCommand(robots));
        robotCommands.put(RobotCommandType.SELF_DIAGNOSTICS, new SelfDiagnosticsCommand(robots));
    }

    @Override
    public void start() {
        System.out.println("Enter robot callSign");
        String callSign = input.write();
        var robot = robots.getRobot(callSign);

        robotCommandsInterface();
        String commandType = input.write();
        var type = parser.getCommand(commandType);
        robotCommands.get(type).execute(robot);
    }

    private void robotCommandsInterface() {
        System.out.println("Enter command type: ");
        System.out.println("1. Dispatch");
        System.out.println("2. Reboot");
        System.out.println("3. Self-Diagnostics");
        System.out.println("4. Delete");
        System.out.println("5. Back to menu");
    }
}
