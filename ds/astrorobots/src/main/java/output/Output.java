package output;

import entity.robot.IRobot;

import java.util.List;

public interface Output {
    void presentRobots(List<IRobot> robots);
}
