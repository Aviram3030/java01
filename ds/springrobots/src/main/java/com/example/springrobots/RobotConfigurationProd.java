package com.example.springrobots;

import com.example.springrobots.markers.HalTools;
import com.example.springrobots.markers.JohnnyTools;
import com.example.springrobots.markers.MaschinenTools;
import com.example.springrobots.markers.TachiTools;
import com.example.springrobots.robots.*;
import com.example.springrobots.tools.ITool;
import org.springframework.context.annotation.*;

import java.util.List;

@ComponentScan
@Configuration
@Profile("prod")
public class RobotConfigurationProd {
    @Bean
    @Scope("prototype")
    public Hal9000 hal9000(@TachiTools List<ITool> tools, RNG rng) {
        return new Hal9000(tools,rng);
    }

    @Bean
    @Scope("prototype")
    public Tachikomas tachikomas(@HalTools List<ITool> tools, RNG rng) {
        return new Tachikomas(tools,rng);
    }

    @Bean
    @Scope("prototype")
    public Johnny5 johnny5(@MaschinenTools List<ITool> tools, RNG rng) {
        return new Johnny5(tools,rng);
    }

    @Bean
    @Scope("prototype")
    public Maschinenmensch maschinenmensch(@JohnnyTools List<ITool> tools, RNG rng) {
        return new Maschinenmensch(tools,rng);
    }
}
