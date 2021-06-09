package entity.tool.state;

import entity.tool.Tool;
import entity.tool.actions.ToolSelfDiagnostics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MalfunctionToolState implements IToolState {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    @Override
    public void repair(Tool tool) {
        executor.submit(new ToolSelfDiagnostics(tool));
    }

    @Override
    public void reboot(Tool tool) {
        tool.reboot();
    }

    @Override
    public void work(Tool tool) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isBroken() {
        return true;
    }

}
