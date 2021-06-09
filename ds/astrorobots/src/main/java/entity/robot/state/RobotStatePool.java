package entity.robot.state;

public class RobotStatePool {
    private final ActiveRobotState active = new ActiveRobotState(this);
    private final FailingRobotState failing = new FailingRobotState(this);
    private final RebootingRobotState rebootingRobotState = new RebootingRobotState(this);

    public ActiveRobotState getActive() {
        return active;
    }

    public FailingRobotState getFailing() {
        return failing;
    }

    public RebootingRobotState getRebootingRobotState() {
        return rebootingRobotState;
    }
}
