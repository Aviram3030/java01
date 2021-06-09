package com.example.springrobots;

import com.example.springrobots.robots.*;
import com.example.springrobots.tools.ITool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@ComponentScan
@EnableConfigurationProperties
@Component
public class FleetConfiguration implements BeanFactoryAware{
    private BeanFactory beanFactory;

    @Bean
    @Scope("prototype")
    public Fleet fleet(FleetProperties fp) {
        var fleet = new Fleet();

        //procureRobbot(fleet, Tachikomas.class, fp.getCallSignstc(), fp.getNamestc());

        var names = fp.getNamestc();
        var callSigns = fp.getCallSignstc();
        for (int i = 0; i < names.size(); i++) {
            var r = beanFactory.getBean(Tachikomas.class);
            r.setCallSign(callSigns.get(i));
            r.setName(names.get(i));
            fleet.addRobot(r);
        }


        names = fp.getNamesh9();
        callSigns = fp.getCallSignsh9();
        for (int i = 0; i < names.size(); i++) {
            var robot = beanFactory.getBean(Hal9000.class);
            addToFleet(robot, names, callSigns, fleet, i);
        }

        names = fp.getNamesj5();
        callSigns = fp.getCallSignsj5();
        for (int i = 0; i < names.size(); i++) {
            var robot = beanFactory.getBean(Johnny5.class);
            addToFleet(robot, names, callSigns, fleet, i);
        }

        names = fp.getNamesmh();
        callSigns = fp.getCallSignsmh();
        for (int i = 0; i < names.size(); i++) {
            var robot = beanFactory.getBean(Maschinenmensch.class);
            addToFleet(robot, names, callSigns, fleet, i);
        }
        return fleet;
    }

    private void addToFleet(SpaceRobot robot, List<String> names, List<String> callSigns, Fleet fleet, int index){
        robot.setName(names.get(index));
        robot.setCallSign(callSigns.get(index));
        fleet.addRobot(robot);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
