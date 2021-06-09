package com.example.springrobots.robots;

import com.example.springrobots.markers.HalTools;
import com.example.springrobots.markers.SpaceRobots;
import com.example.springrobots.tools.ITool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@SpaceRobots
public class Hal9000 extends SpaceRobot {
    public Hal9000(List<ITool> tools, RNG rng) {
        super(tools, rng);
    }
}
