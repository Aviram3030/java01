package entity.robot.state;

import entity.robot.actions.RobotReboot;
import entity.robot.actions.RobotDispatch;
import entity.robot.SpaceRobot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActiveRobotState implements IRobotState {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final RobotStatePool statePool;

    public ActiveRobotState(RobotStatePool statePool) {
        this.statePool = statePool;
    }

    @Override
    public void reboot(SpaceRobot robot) {
        robot.setState(statePool.getRebootingRobotState());
        executor.submit(new RobotReboot(robot, statePool));
    }

    @Override
    public void dispatch(SpaceRobot robot) {
        Thread thread = new Thread(new RobotDispatch(robot, statePool));
        robot.setState(new WorkingRobotState(thread));
        thread.start();
    }

    @Override
    public void selfDiagnostic(SpaceRobot robot) {
        robot.selfDiagnosticSequence();
    }
}
