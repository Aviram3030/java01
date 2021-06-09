package robotcommands;

import entity.robot.IRobot;

public class SelfDiagnosticsCommand implements IRobotCommand{
    private final RobotsGroup robots;

    public SelfDiagnosticsCommand(RobotsGroup robots) {
        this.robots = robots;
    }

    @Override
    public void execute(IRobot robot) {
        robots.selfDiagnostic(robot);
    }
}
