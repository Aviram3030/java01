package com.example.springrobots.tools;

import com.example.springrobots.markers.HalTools;
import com.example.springrobots.markers.JohnnyTools;
import com.example.springrobots.markers.TachiTools;

@HalTools
@TachiTools
@JohnnyTools
public class LaserCutter extends Tool{
    public LaserCutter() {
        super("Laser cutter");
    }
}
