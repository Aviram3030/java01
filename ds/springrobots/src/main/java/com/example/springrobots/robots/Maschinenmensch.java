package com.example.springrobots.robots;

import com.example.springrobots.markers.SpaceRobots;
import com.example.springrobots.tools.ITool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@SpaceRobots
public class Maschinenmensch extends SpaceRobot {
    public Maschinenmensch(List<ITool> tools, RNG rng) {
        super(tools, rng);
    }
}
