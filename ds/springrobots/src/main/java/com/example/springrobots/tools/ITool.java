package com.example.springrobots.tools;

public interface ITool {
    void work();
    void selfDiagnostic();
    void reboot();
    boolean isBroken();
}
