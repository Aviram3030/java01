package entity.tool.actions;

import entity.tool.Tool;
import entity.tool.state.MalfunctionToolState;
import entity.tool.state.ReadyToolState;

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
            tool.setState(new MalfunctionToolState());
        }
        else{
            tool.setState(new ReadyToolState());
        }
        System.out.printf("Self diagnostics for %s is finished", tool.getName());
    }
}
