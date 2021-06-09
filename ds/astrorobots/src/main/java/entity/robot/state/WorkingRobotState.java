package entity.robot.state;

import entity.robot.SpaceRobot;
import exceptions.RobotWorkingException;

public class WorkingRobotState implements IRobotState{
    private final Thread workingThread;

    public WorkingRobotState(Thread workingThread) {
        this.workingThread = workingThread;
    }

    @Override
    public void reboot(SpaceRobot robot) {
        workingThread.interrupt();
    }

    @Override
    public void dispatch(SpaceRobot robot) {
        throw new RobotWorkingException(robot.getName());
    }

    @Override
    public void selfDiagnostic(SpaceRobot robot) {
        throw new RobotWorkingException(robot.getName());
    }
}
