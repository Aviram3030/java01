package com.example.springrobots.robots;

import com.example.springrobots.robots.state.IRobotState;
import com.example.springrobots.tools.ITool;
import com.example.springrobots.tools.state.ToolState;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SpaceRobot implements IRobot {
    private final List<ITool> tools;
    private final RNG rng;
    private String name;
    private String callSign;

    private IRobotState state;
    private Lock lock = new ReentrantLock();

    public SpaceRobot(List<ITool> tools, RNG rng) {
        this.tools = tools;
        this.rng = rng;
        initialState();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    @Override
    public void reboot() {
        lock.lock();
        try {
            state.reboot(this);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void dispatch() {
        lock.lock();
        try {
            state.dispatch(this);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void selfDiagnostic() {
        lock.lock();
        System.out.printf("\n%s starts selfDiagnostics\n", name);
        try {
            state.selfDiagnostic(this);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        return name;
    }

    public void dispatchSequence() {
        System.out.printf("\n%s dispatching...\n", name);
        for (ITool tool : tools) {
            tool.work();
        }
    }

    public void rebootSequence() {
        System.out.printf("\n%s rebooting...\n", name);
        for (ITool tool : tools) {
            tool.selfDiagnostic();
        }
    }

    public void setState(IRobotState state) {
        this.state = state;
    }

    public void selfDiagnosticSequence() {
        for (ITool tool : tools) {
            tool.selfDiagnostic();
        }
    }

    public boolean isBroken() {
        for (ITool tool : tools) {
            if (tool.isBroken()) {
                return true;
            }
        }
        return false;
    }

    public void cosmicRays(){
        lock.lock();
        state.cosmicRays(this);
        lock.unlock();
    }

    private void initialState() {
        state = rng.execute();
    }

    public void cosmicRaysSequence() {
        state = rng.execute();
    }

    @Override
    public String toString() {
        return "SpaceRobot{" +
                "tools=" + tools +
                ", name='" + name + '\'' +
                ", callSign='" + callSign + '\'' +
                ", state=" + state +
                '}';
    }

}
