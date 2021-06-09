package entity.robot.state;

import entity.robot.SpaceRobot;

public interface IRobotState {
    void reboot(SpaceRobot robot);
    void dispatch(SpaceRobot robot);
    void selfDiagnostic(SpaceRobot robot);
}
