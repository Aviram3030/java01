package entity.tool.state;

import entity.tool.Tool;

public interface IToolState {
    void work(Tool tool);
    void repair(Tool tool);
    void reboot(Tool tool);
    boolean isBroken();
}
