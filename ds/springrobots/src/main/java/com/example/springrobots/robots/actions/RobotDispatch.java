package com.example.springrobots.robots.actions;

import com.example.springrobots.robots.SpaceRobot;
import com.example.springrobots.robots.state.RobotState;

import java.util.Random;

public class RobotDispatch implements Runnable {
    private int waitingTime;
    private final SpaceRobot robot;

    public RobotDispatch(SpaceRobot robot) {
        this.robot = robot;
    }


    @Override
    public void run() {
        robot.setState(RobotState.WORKING);
        robot.dispatchSequence();

        var random = new Random();
        waitingTime = random.nextInt(150);
        try {
            Thread.sleep((waitingTime + 30) * 1_000_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (robot.isBroken()) {
            robot.setState(RobotState.FAILING);
        } else {
            robot.setState(RobotState.ACTIVE);
        }

        System.out.printf("Dispatch for %s is finished\n", robot.getName());
    }
}
