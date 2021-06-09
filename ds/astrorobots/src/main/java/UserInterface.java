import factory.IRobotFactory;
import input.Input;
import output.Output;
import usercommands.UserCommandsService;

public class UserInterface {
    private final Input input;
    private final Output output;
    private final UserCommandsService service;

    public UserInterface(Input input, Output output, IRobotFactory robotFactory){
        this.input = input;
        this.output = output;
        service = new UserCommandsService(input, output, robotFactory);
    }

    public void start(){
        while(true) {
            System.out.println("Hello, please pick a user command");
            System.out.println("1. Provision a new robot");
            System.out.println("2. Issue commands to robot");
            System.out.println("3. Quit");

            String txt = input.write();
            if("3".equals(txt)){
                break;
            }
            service.execute(txt);
        }
    }
}
