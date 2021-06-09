package com.example.springrobots.tools.state;

import com.example.springrobots.tools.Tool;

public interface IToolState {
    void work(Tool tool);
    void repair(Tool tool);
    void reboot(Tool tool);
    boolean isBroken();
}
