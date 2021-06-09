package entity.robot;

import entity.tool.Disruptor;
import entity.tool.Replicator;

public class Maschinenmensch extends SpaceRobot {
    public Maschinenmensch(String name, String callSign) {
        super(name, callSign);
        tools.add(new Replicator());
        tools.add(new Disruptor());
    }
}
