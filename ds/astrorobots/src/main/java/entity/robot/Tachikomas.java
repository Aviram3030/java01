package entity.robot;

import entity.tool.LaserCutter;
import entity.tool.StaticBrush;

public class Tachikomas extends SpaceRobot {
    public Tachikomas(String name, String callSign) {
        super(name, callSign);
        tools.add(new LaserCutter());
        tools.add(new StaticBrush());
    }
}
