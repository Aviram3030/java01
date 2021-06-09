package com.example.springrobots.robots;

import com.example.springrobots.robots.state.IRobotState;
import com.example.springrobots.robots.state.RobotState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RNG {
    private final Random random = new Random();

    @Value("${faults.range:100}")
    private int range;

    @Value("${faults.perc:80}")
    private int p;


    public IRobotState execute() {
        if (random.nextInt(range) > p) {
            return RobotState.ACTIVE;
        }
        return RobotState.FAILING;
    }
}
