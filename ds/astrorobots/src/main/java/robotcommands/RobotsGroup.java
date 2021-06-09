package robotcommands;

import entity.robot.IRobot;
import factory.IRobotFactory;
import factory.RobotFactory;
import factory.RobotModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RobotsGroup {
    private final Lock lock = new ReentrantLock();
    private final Map<String, IRobot> robots = new ConcurrentHashMap<>();
    private final IRobotFactory factory;

    public RobotsGroup(IRobotFactory factory) {
        this.factory = factory;
    }

    public void addRobot(RobotModel robotModel, String callSign, String name){
        lock.lock();
        if(robots.get(callSign) != null){
            System.out.println("There is already a robot with the same callSign");
            lock.unlock();
            return;
        }

        var robot = factory.createRobot(robotModel, name, callSign);
        robots.put(callSign, robot);
        lock.unlock();
    }

    public IRobot getRobot(String callSign){
        lock.lock();
        var robot = robots.get(callSign);
        lock.unlock();
        return robot;
    }

    public void removeRobot(String callSign, IRobot robot){
        lock.lock();
        if(robots.containsValue(robot)) {
            robots.remove(callSign, robot);
        }
        else{
            unavailable();
        }
        lock.unlock();
    }

    public void selfDiagnostic(IRobot robot){
        lock.lock();
        if(robots.containsValue(robot)) {
            robot.selfDiagnostic();
        }
        else{
            unavailable();
        }
        lock.unlock();
    }

    public void dispatch(IRobot robot){
        lock.lock();
        if(robots.containsValue(robot)) {
            robot.dispatch();
        }
        else{
            unavailable();
        }
        lock.unlock();
    }

    public void reboot(IRobot robot){
        lock.lock();
        if(robots.containsValue(robot)) {
            robot.reboot();
        }
        else{
            unavailable();
        }
        lock.unlock();
    }


    //TODO : Catch null exception ?
    private void unavailable(){
        System.out.println("The current robot is unavailable");
    }
}
