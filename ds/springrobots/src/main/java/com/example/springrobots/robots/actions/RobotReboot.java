package com.example.springrobots.robots.actions;

import com.example.springrobots.robots.SpaceRobot;
import com.example.springrobots.robots.state.RobotState;

import java.util.Random;

public class RobotReboot implements Runnable{
    private final SpaceRobot robot;

    public RobotReboot(SpaceRobot robot) {
        this.robot = robot;
    }


    @Override
    public void run() {
        robot.setState(RobotState.REBOOTING);
        robot.rebootSequence();

        var random = new Random();
        int waitingTime = random.nextInt(5);
        try {
            Thread.sleep((waitingTime + 1) * 1_000_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.setState(RobotState.ACTIVE);
        System.out.printf("Reboot for %s is finished\n", robot.getName());
    }
}
