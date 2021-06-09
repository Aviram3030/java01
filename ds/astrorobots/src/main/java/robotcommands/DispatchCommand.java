package robotcommands;

import entity.robot.IRobot;

//TODO: robot null
public class DispatchCommand implements IRobotCommand{
    private final RobotsGroup robots;

    public DispatchCommand(RobotsGroup robots) {
        this.robots = robots;
    }

    @Override
    public void execute(IRobot robot) {
        robots.dispatch(robot);
    }
}
