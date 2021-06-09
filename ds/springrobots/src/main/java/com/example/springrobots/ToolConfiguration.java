package com.example.springrobots;

import com.example.springrobots.tools.Disruptor;
import com.example.springrobots.tools.LaserCutter;
import com.example.springrobots.tools.Replicator;
import com.example.springrobots.tools.StaticBrush;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ToolConfiguration {
    @Bean
    @Scope("prototype")
    public LaserCutter laserCutter() {
        return new LaserCutter();
    }

    @Bean
    @Scope("prototype")
    public Replicator replicator() {
        return new Replicator();
    }

    @Bean
    @Scope("prototype")
    public StaticBrush staticBrush() {
        return new StaticBrush();
    }

    @Bean
    @Scope("prototype")
    public Disruptor disruptor() {
        return new Disruptor();
    }
}
