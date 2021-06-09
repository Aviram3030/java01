package entity.robot;

import entity.tool.Disruptor;
import entity.tool.LaserCutter;
import entity.tool.Replicator;

public class Hal9000 extends SpaceRobot {
    public Hal9000(String name, String callSign) {
        super(name, callSign);
        tools.add(new Replicator());
        tools.add(new Disruptor());
        tools.add(new LaserCutter());
    }
}
