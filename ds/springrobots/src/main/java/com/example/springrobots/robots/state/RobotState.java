package com.example.springrobots.robots.state;

import com.example.springrobots.exceptions.RobotFailingException;
import com.example.springrobots.exceptions.RobotRebootingException;
import com.example.springrobots.exceptions.RobotWorkingException;
import com.example.springrobots.robots.SpaceRobot;
import com.example.springrobots.robots.actions.RobotDispatch;
import com.example.springrobots.robots.actions.RobotReboot;

public enum RobotState implements IRobotState {
    ACTIVE {
        @Override
        public void reboot(SpaceRobot robot) {
            new Thread(new RobotReboot(robot)).start();
        }

        @Override
        public void dispatch(SpaceRobot robot) {
            new Thread(new RobotDispatch(robot)).start();
        }

        @Override
        public void selfDiagnostic(SpaceRobot robot) {
            robot.selfDiagnosticSequence();
        }

        @Override
        public void cosmicRays(SpaceRobot robot) {
            robot.cosmicRaysSequence();
        }
    },

    FAILING {
        @Override
        public void dispatch(SpaceRobot robot){
            throw new RobotFailingException(robot.getName());
        }

        @Override
        public void reboot(SpaceRobot robot) {
            new Thread(new RobotReboot(robot)).start();
        }

        @Override
        public void selfDiagnostic(SpaceRobot robot) {
            robot.selfDiagnosticSequence();
        }

        @Override
        public void cosmicRays(SpaceRobot robot) {

        }
    },

    REBOOTING {
        @Override
        public void dispatch(SpaceRobot robot){
            throw new RobotRebootingException(robot.getName());
        }

        @Override
        public void selfDiagnostic(SpaceRobot robot){
            throw new RobotRebootingException(robot.getName());
        }

        @Override
        public void cosmicRays(SpaceRobot robot) {

        }

        public void reboot(SpaceRobot robot){
            throw new RobotRebootingException(robot.getName());
        }
    },

    WORKING {
        @Override
        public void reboot(SpaceRobot robot) {
            throw new RobotWorkingException(robot.getName());
        }

        @Override
        public void dispatch(SpaceRobot robot) {
            throw new RobotWorkingException(robot.getName());
        }

        @Override
        public void selfDiagnostic(SpaceRobot robot) {
            throw new RobotWorkingException(robot.getName());
        }

        @Override
        public void cosmicRays(SpaceRobot robot) {

        }
    }


}
