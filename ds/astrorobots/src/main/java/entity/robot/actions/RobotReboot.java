package entity.robot.actions;

import entity.robot.SpaceRobot;
import entity.robot.state.ActiveRobotState;
import entity.robot.state.RobotStatePool;

import java.util.Random;

public class RobotReboot implements Runnable{
    private int waitingTime;
    private final SpaceRobot robot;
    private final RobotStatePool statePool;

    public RobotReboot(SpaceRobot robot, RobotStatePool statePool) {
        this.robot = robot;
        this.statePool = statePool;
    }


    @Override
    public void run() {
        robot.rebootSequence();
        var random = new Random();
        waitingTime = random.nextInt(5);
        try {
            Thread.currentThread().wait((waitingTime + 1) * 1_000_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.setState(statePool.getActive());
        System.out.printf("Reboot for %s is finished", robot.getName());
    }
}
