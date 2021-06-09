package entity.robot.state;

import entity.robot.SpaceRobot;
import exceptions.RobotFailingException;

public class FailingRobotState implements IRobotState {
    private final RobotStatePool statePool;

    public FailingRobotState(RobotStatePool statePool) {
        this.statePool = statePool;
    }

    @Override
    public void dispatch(SpaceRobot robot){
        throw new RobotFailingException(robot.getName());
    }

    @Override
    public void reboot(SpaceRobot robot) {
        robot.rebootSequence();
    }

    @Override
    public void selfDiagnostic(SpaceRobot robot) {
        robot.selfDiagnosticSequence();
    }
}
