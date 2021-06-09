package entity.robot;

import entity.robot.state.RobotStatePool;
import entity.tool.ITool;

import entity.robot.state.IRobotState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SpaceRobot implements IRobot {
    private RobotStatePool statePool = new RobotStatePool();
    private final String name;
    private final String callSign;
    private IRobotState state = statePool.getActive();
    protected List<ITool> tools = new ArrayList<>();
    private Lock lock = new ReentrantLock();

    public SpaceRobot(String name, String callSign) {
        this.name = name;
        this.callSign = callSign;
    }

    @Override
    public void selfDiagnostic() {
        lock.lock();
        System.out.printf("Robot %s starts selfDiagnostics\n", name);
        try {
            state.selfDiagnostic(this);
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void reboot() {
        lock.lock();
        System.out.printf("Robot %s starts reboot\n", name);
        try{
        state.reboot(this);
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void dispatch() {
        lock.lock();
        System.out.printf("Robot %s starts dispatch\n", name);
        try{
            state.dispatch(this);
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
        finally {
            lock.unlock();
        }
    }

    public void setState(IRobotState state){
        this.state = state;
    }

    public boolean isBroken() {
        for (ITool tool : tools) {
            if (tool.isBroken()) {
                return true;
            }
        }

        return false;
    }

    public void selfDiagnosticSequence() {
        for (ITool tool : tools) {
            tool.repair();
        }
    }

    public void dispatchSequence() {
        for (ITool tool : tools) {
            tool.work();
        }
    }

    public void rebootSequence(){
        for (ITool tool : tools) {
            tool.reboot();
        }
    }

    public String getName(){
        return name;
    }
}
