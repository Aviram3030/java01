package factory;

import entity.robot.IRobot;

public interface IRobotFactory {
    IRobot createRobot(RobotModel model, String name, String callSign);
}
