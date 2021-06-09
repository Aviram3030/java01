package exceptions;

public class RobotRebootingException extends ArithmeticException{
    public RobotRebootingException(String name) {
        super("Robot " + name + " is rebooting right now");
    }
}
