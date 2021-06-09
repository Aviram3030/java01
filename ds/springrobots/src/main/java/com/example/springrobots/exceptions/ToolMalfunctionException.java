package com.example.springrobots.exceptions;

public class ToolMalfunctionException extends ArithmeticException{
    public ToolMalfunctionException(String name) {
        super("tool " + name + " is not working right now");
    }
}
