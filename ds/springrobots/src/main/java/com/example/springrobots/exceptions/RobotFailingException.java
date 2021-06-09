package com.example.springrobots.exceptions;

public class RobotFailingException extends ArithmeticException{
    public RobotFailingException(String name) {
        super("Robot " + name + " is in failing state right now");
    }
}
