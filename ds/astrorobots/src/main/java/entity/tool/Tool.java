package entity.tool;

import entity.tool.state.IToolState;
import entity.tool.state.MalfunctionToolState;
import entity.tool.state.ReadyToolState;

import java.util.Random;

public class Tool implements ITool {
    private final String toolName;
    private IToolState state = new ReadyToolState();

    public Tool(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public void work() {
        System.out.printf("%s is working\n", toolName);
        state.work(this);
    }

    @Override
    public void repair() {
        if(isBroken()) {
            System.out.printf("Starting self-healing on tool %s\n", toolName);
            state.repair(this);
        }
    }

    public boolean isBroken(){
        return state.isBroken();
    }

    @Override
    public void reboot() {
        state.reboot(this);
    }


    private void cosmicRays(){
        Random random = new Random();
        if(random.nextInt(10) > 8){
            state = new MalfunctionToolState();
        }
    }

    public String getName(){
        return toolName;
    }

    public void setState(IToolState state) {
        this.state = state;
    }
}
