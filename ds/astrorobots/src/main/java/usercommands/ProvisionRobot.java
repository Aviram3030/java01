package usercommands;

import input.Input;
import output.Output;
import robotcommands.RobotsGroup;

public class ProvisionRobot implements IUserCommand{
    private final RobotsGroup robots;
    private final Input input;
    private final Output output;

    public ProvisionRobot(RobotsGroup robots, Input input, Output output) {
        this.robots = robots;
        this.input = input;
        this.output = output;
    }

    //TODO: get input
    @Override
    public void start() {
        robots.addRobot(null,null,null);
    }
}
