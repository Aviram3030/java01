package com.example.springrobots.exceptions;

public class RobotWorkingException extends ArithmeticException{
    public RobotWorkingException(String name) {
        super("Robot " + name + " is working right now");
    }
}
