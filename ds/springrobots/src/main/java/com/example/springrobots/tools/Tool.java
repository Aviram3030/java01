package com.example.springrobots.tools;

import com.example.springrobots.tools.state.IToolState;
import com.example.springrobots.tools.state.ToolState;

import java.util.Random;

public class Tool implements ITool{
    private final String toolName;
    private IToolState state = ToolState.READY;

    public Tool(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public void work() {
        System.out.printf("%s is working\n", toolName);
        try {
            state.work(this);
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void selfDiagnostic() {
        System.out.printf("Starting self-healing on tool %s\n", toolName);
        try {
            state.repair(this);
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void reboot() {
        try {
            state.reboot(this);
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }



    public boolean isBroken(){
        return state.isBroken();
    }

    public String getName(){
        return toolName;
    }

    public void setState(IToolState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "toolName='" + toolName + '\'' +
                ", state=" + state +
                '}';
    }
}
