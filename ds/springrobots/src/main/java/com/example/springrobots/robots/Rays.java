package com.example.springrobots.robots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Rays {
    @Autowired
    private Fleet fleet;

    @Scheduled(fixedRate = 5000)
    public void impact(){
        System.out.println("Cosmic rays striking on...");
        var spaceRobots = fleet.getRobots();
        for (SpaceRobot spaceRobot : spaceRobots) {
            spaceRobot.cosmicRays();
        }
    }

}

