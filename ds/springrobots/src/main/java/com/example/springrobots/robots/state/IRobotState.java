package com.example.springrobots.robots.state;

import com.example.springrobots.robots.SpaceRobot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface IRobotState {
    void reboot(SpaceRobot robot);
    void dispatch(SpaceRobot robot);
    void selfDiagnostic(SpaceRobot robot);
    void cosmicRays(SpaceRobot robot);
}
