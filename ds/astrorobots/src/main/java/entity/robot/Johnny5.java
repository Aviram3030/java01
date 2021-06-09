package entity.robot;

import entity.tool.LaserCutter;
import entity.tool.StaticBrush;

public class Johnny5 extends SpaceRobot {
    public Johnny5(String name, String callSign) {
        super(name, callSign);
        tools.add(new LaserCutter());
        tools.add(new StaticBrush());
    }
}
