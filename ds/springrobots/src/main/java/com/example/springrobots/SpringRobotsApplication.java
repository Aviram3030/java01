package com.example.springrobots;

import com.example.springrobots.robots.Fleet;
import com.example.springrobots.robots.SpaceRobot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

//@SpringBootApplication
public class SpringRobotsApplication {

    public static void main(String[] args) {
        var app = new AnnotationConfigApplicationContext(AppConfiguration.class);

        var fleet = app.getBean("fleet", Fleet.class);
        try {
            fleet.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
