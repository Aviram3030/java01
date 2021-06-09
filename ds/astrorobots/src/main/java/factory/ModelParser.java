package factory;

import java.util.HashMap;

public class ModelParser {
    private HashMap<String, RobotModel> models = new HashMap<>();

    public ModelParser(){
        models.put("1", RobotModel.HAL9000);
        models.put("2", RobotModel.TACHIKOMAS);
        models.put("3", RobotModel.JOHNNY5);
        models.put("4", RobotModel.MASCHINENNMENSCH);
    }

    public RobotModel getModel(String txt){
        return models.get(txt);
    }
}
