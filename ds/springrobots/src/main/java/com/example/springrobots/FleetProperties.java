package com.example.springrobots;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "fleet")
public class FleetProperties {

    private final List<String> namesh9 = new ArrayList<>();
    private final List<String> callSignsh9 = new ArrayList<>();

    private final List<String> namesj5 = new ArrayList<>();
    private final List<String> callSignsj5 = new ArrayList<>();

    private final List<String> namesmh = new ArrayList<>();
    private final List<String> callSignsmh = new ArrayList<>();

    private final List<String> namestc = new ArrayList<>();
    private final List<String> callSignstc = new ArrayList<>();

    public List<String> getNamesh9() {
        return namesh9;
    }

    public List<String> getCallSignsh9() {
        return callSignsh9;
    }

    public List<String> getNamesj5() {
        return namesj5;
    }

    public List<String> getCallSignsj5() {
        return callSignsj5;
    }

    public List<String> getNamesmh() {
        return namesmh;
    }

    public List<String> getCallSignsmh() {
        return callSignsmh;
    }

    public List<String> getNamestc() {
        return namestc;
    }

    public List<String> getCallSignstc() {
        return callSignstc;
    }

    //    private final List<String> names = new ArrayList<>();
//    private final List<String> callSigns = new ArrayList<>();
//    private final List<String> models = new ArrayList<>();
//
//    public List<String> getNames() {
//        return names;
//    }
//
//    public List<String> getCallSigns() {
//        return callSigns;
//    }
//
//    public List<String> getModels() {
//        return models;
//    }
}
