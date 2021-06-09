package robotcommands;

import entity.robot.IRobot;

public class RebootCommand implements IRobotCommand{
    private final RobotsGroup robots;

    public RebootCommand(RobotsGroup robots) {
        this.robots = robots;
    }

    @Override
    public void execute(IRobot robot) {
        robots.reboot(robot);
    }
}
