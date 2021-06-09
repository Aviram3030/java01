package com.example.springrobots.tools.actions;

import com.example.springrobots.tools.state.ToolState;

import com.example.springrobots.tools.Tool;
import java.util.Random;

public class ToolSelfDiagnostics implements Runnable{
    private final Tool tool;

    public ToolSelfDiagnostics(Tool tool) {
        this.tool = tool;
    }

    @Override
    public void run() {
        Random random = new Random();
        if(random.nextInt(10) > 8){
            tool.setState(ToolState.MALFUNCTION);
        }
        else{
            tool.setState(ToolState.READY);
        }
        System.out.printf("Self diagnostics for %s is finished\n", tool.getName());
    }
}
