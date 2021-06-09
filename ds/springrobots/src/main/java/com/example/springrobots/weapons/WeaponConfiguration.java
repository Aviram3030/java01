package com.example.springrobots.weapons;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class WeaponConfiguration {
    @Bean
    @Primary
    public Pistol pistol(){
        return new Pistol();
    }

    @Bean
    @Qualifier("GU")
    public Gun gun(){
        return new Gun();
    }

    @Bean
    @Qualifier("BZ")
    public Bazooka bazooka(){
        return new Bazooka();
    }
}
