package entity.robot.actions;

import entity.robot.SpaceRobot;
import entity.robot.state.ActiveRobotState;
import entity.robot.state.FailingRobotState;
import entity.robot.state.RobotStatePool;

import java.util.Random;

public class RobotDispatch implements Runnable{
    private int waitingTime;
    private final SpaceRobot robot;
    private final RobotStatePool statePool;

    public RobotDispatch(SpaceRobot robot, RobotStatePool statePool) {
        this.robot = robot;
        this.statePool = statePool;
    }


    @Override
    public void run() {
        robot.dispatchSequence();
        var random = new Random();
        waitingTime = random.nextInt(150);
        try {
            Thread.sleep((waitingTime + 30) * 1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(robot.isBroken()){
            robot.setState(statePool.getFailing());
        }
        else{
            robot.setState(statePool.getActive());
        }

        System.out.printf("Dispatch for %s is finished\n", robot.getName());
    }
}
