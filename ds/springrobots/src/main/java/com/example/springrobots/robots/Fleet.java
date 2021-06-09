package com.example.springrobots.robots;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Fleet {
    private final List<SpaceRobot> robots = new ArrayList<>();

    public void addRobot(SpaceRobot robot){
        robots.add(robot);
    }

    public List<SpaceRobot> getRobots() {
        return robots;
    }

    public void run() throws InterruptedException {
        for (SpaceRobot robot : robots) {
            robot.reboot();
            Thread.sleep(1000);
        }

        for (SpaceRobot robot : robots) {
            robot.dispatch();
            Thread.sleep(1000);
        }

        for (SpaceRobot robot : robots) {
            robot.selfDiagnostic();
            Thread.sleep(1000);
        }
    }
}
