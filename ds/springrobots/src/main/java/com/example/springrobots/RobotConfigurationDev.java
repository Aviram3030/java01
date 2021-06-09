package com.example.springrobots;

import com.example.springrobots.markers.HalTools;
import com.example.springrobots.markers.JohnnyTools;
import com.example.springrobots.markers.MaschinenTools;
import com.example.springrobots.markers.TachiTools;
import com.example.springrobots.robots.*;
import com.example.springrobots.tools.*;
import org.springframework.context.annotation.*;

import java.util.List;

@ComponentScan
@Configuration
@Profile("dev")
public class RobotConfigurationDev {

    @Bean
    @Scope("prototype")
    public Hal9000 hal9000(@HalTools List<ITool> tools, RNG rng) {
        return new Hal9000(tools, rng);
    }

    @Bean
    @Scope("prototype")
    public Tachikomas tachikomas(@TachiTools List<ITool> tools, RNG rng) {
        return new Tachikomas(tools, rng);
    }

    @Bean
    @Scope("prototype")
    public Johnny5 johnny5(@JohnnyTools List<ITool> tools, RNG rng) {
        return new Johnny5(tools, rng);
    }

    @Bean
    @Scope("prototype")
    public Maschinenmensch maschinenmensch(@MaschinenTools List<ITool> tools, RNG rng) {
        return new Maschinenmensch(tools, rng);
    }

//    @Bean
//    @Scope("prototype")
//    public List<Tool> hal9000Tools() {
//        List<Tool> tools = new ArrayList<>();
//        tools.add(replicator());
//        tools.add(laserCutter());
//        tools.add(staticBrush());
//        return tools;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public List<Tool> tachikomasTools() {
//        List<Tool> tools = new ArrayList<>();
//        tools.add(replicator());
//        tools.add(laserCutter());
//        return tools;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public List<Tool> johnny5Tools() {
//        List<Tool> tools = new ArrayList<>();
//        tools.add(laserCutter());
//        tools.add(staticBrush());
//        return tools;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public List<Tool> maschinenmenschTools() {
//        List<Tool> tools = new ArrayList<>();
//        tools.add(replicator());
//        tools.add(disruptor());
//        return tools;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public SpaceRobotBuilder spaceRobotBuilder() {
//        return new SpaceRobotBuilder();
//    }
//
//    @Bean("hal9000Fleet")
//    @Scope("prototype")
//    public Fleet hal9000Fleet(@Value("${fleet.hal.quantity}") int length) {
//        var fleet = new Fleet();
//        for (int i = 0; i < length; i++) {
//            var robot = hal9000(replicator(), laserCutter(), staticBrush());
//            robot.setName("hal-" + i);
//            fleet.addRobot(robot);
//        }
//        return fleet;
//    }
//
//    @Bean("tachikomasFleet")
//    @Scope("prototype")
//    public Fleet tachikomasFleet(@Value("${fleet.tachi.quantity}") int length) {
//        var fleet = new Fleet();
//        for (int i = 0; i < length; i++) {
//            var robot = tachikomas(replicator(), laserCutter());
//            robot.setName("Tachi-" + i);
//            fleet.addRobot(robot);
//        }
//        return fleet;
//    }
//
//    @Bean("johnny5Fleet")
//    @Scope("prototype")
//    public Fleet johnny5Fleet(@Value("${fleet.johnny.quantity}") int length) {
//        var fleet = new Fleet();
//        for (int i = 0; i < length; i++) {
//            var robot = johnny5(laserCutter(), staticBrush());
//            robot.setName("Johnny-" + i);
//            fleet.addRobot(robot);
//        }
//        return fleet;
//    }
//
//    @Bean("maschinenmenscheFleet")
//    @Scope("prototype")
//    public Fleet maschinenmenscheFleet(@Value("${fleet.masch.quantity}") int length) {
//        var fleet = new Fleet();
//        for (int i = 0; i < length; i++) {
//            var robot = maschinenmensch(replicator(), disruptor());
//            robot.setName("Maschin-" + i);
//            fleet.addRobot(robot);
//        }
//        return fleet;
//    }
//
//    @Bean
//    @Scope("prototype")
//    public Fleet spaceRobots(Fleet maschinenmenscheFleet, Fleet johnny5Fleet,
//                                        Fleet hal9000Fleet, Fleet tachikomasFleet, FleetProperties fleetProperties) {
//        var fleet = new Fleet();
////        int count = 0;
////        var list = maschinenmenscheFleet.getRobots();
////        List<String> callSigns = fleetProperties.getCallSigns();
////        List<String> names = fleetProperties.getNames();
////
////        for (SpaceRobot spaceRobot : list) {
////            spaceRobot.setName(names.get(count));
////            spaceRobot.setCallSign(callSigns.get(count++));
////            fleet.addRobot(spaceRobot);
////        }
//        fleet.addRobots(maschinenmenscheFleet);
//        fleet.addRobots(johnny5Fleet);
//        fleet.addRobots(hal9000Fleet);
//        fleet.addRobots(tachikomasFleet);
//
//        return fleet;
//    }
}
