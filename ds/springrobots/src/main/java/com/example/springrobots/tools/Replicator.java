package com.example.springrobots.tools;

import com.example.springrobots.markers.HalTools;
import com.example.springrobots.markers.MaschinenTools;
import com.example.springrobots.markers.TachiTools;

@HalTools
@TachiTools
@MaschinenTools
public class Replicator extends Tool{
    public Replicator() {
        super("Replicator");
    }
}
