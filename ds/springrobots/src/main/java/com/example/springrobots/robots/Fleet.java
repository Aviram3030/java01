package com.example.springrobots.robots;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private List<SpaceRobot> robots = new ArrayList<>();

    public void addRobot(SpaceRobot robot){
        robots.add(robot);
    }

    public List<SpaceRobot> getRobots() {
        return robots;
    }

    public void run(){
        for (SpaceRobot robot : robots) {
            robot.reboot();
        }

        for (SpaceRobot robot : robots) {
            robot.dispatch();
        }

        for (SpaceRobot robot : robots) {
            robot.selfDiagnostic();
        }
    }
}
