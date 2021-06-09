package robotcommands;

import entity.robot.IRobot;

public class DeleteCommand implements IRobotCommand{
    private final RobotsGroup robots;

    public DeleteCommand(RobotsGroup robots) {
        this.robots = robots;
    }


    //TODO: get robot callSign
    @Override
    public void execute(IRobot robot) {
        robots.removeRobot(null, robot);
    }
}
