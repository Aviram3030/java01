package entity.robot.state;

import entity.robot.SpaceRobot;
import exceptions.RobotRebootingException;

public class RebootingRobotState implements IRobotState {
    private final RobotStatePool statePool;

    public RebootingRobotState(RobotStatePool statePool) {
        this.statePool = statePool;
    }

    @Override
    public void dispatch(SpaceRobot robot){
        throw new RobotRebootingException(robot.getName());
    }

    @Override
    public void selfDiagnostic(SpaceRobot robot){
        throw new RobotRebootingException(robot.getName());
    }

    public void reboot(SpaceRobot robot){
        throw new RobotRebootingException(robot.getName());
    }
}
