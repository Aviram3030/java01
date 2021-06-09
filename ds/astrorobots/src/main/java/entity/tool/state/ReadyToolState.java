package entity.tool.state;
import entity.tool.Tool;
import java.util.Random;

public class ReadyToolState implements IToolState {

    @Override
    public void repair(Tool tool) {
    }

    @Override
    public void work(Tool tool) {
        Random random = new Random();
        if(random.nextInt(10) > 7){
            tool.setState(new MalfunctionToolState());
        }
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public void reboot(Tool tool) {

    }

}
