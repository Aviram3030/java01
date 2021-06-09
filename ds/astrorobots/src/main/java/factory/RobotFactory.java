package factory;

import entity.robot.*;

public class RobotFactory implements IRobotFactory{

    @Override
    public IRobot createRobot(RobotModel model, String name, String callSign){
        return switch(model){
            case HAL9000 -> new Hal9000(name, callSign);
            case JOHNNY5 -> new Johnny5(name, callSign);
            case MASCHINENNMENSCH -> new Maschinenmensch(name, callSign);
            case TACHIKOMAS -> new Tachikomas(name, callSign);
        };
    }
}
