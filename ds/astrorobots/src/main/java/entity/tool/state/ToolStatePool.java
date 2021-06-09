package entity.tool.state;

public class ToolStatePool {
    private MalfunctionToolState malfunction = new MalfunctionToolState();
    private ReadyToolState ready = new ReadyToolState();

    public MalfunctionToolState getMalfunctionState(){
        return malfunction;
    }

    public ReadyToolState getReadyToolState(){
        return ready;
    }
}
