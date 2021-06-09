package entity.robot;

import org.junit.Test;

public class RobotDispatchTest {

    @Test
    public void dispatch() {
        var robot = new Tachikomas("Bob", "Go");
        robot.selfDiagnostic();
        System.out.println();
        robot.dispatch();
        System.out.println();
        robot.selfDiagnostic();
        System.out.println();
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}