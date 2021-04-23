package experis.ds.tests;

import experis.ds.Scheduler;
import experis.ds.SleepCalculatorType;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SuspendAllTest {

    Scheduler scheduler = new Scheduler();

    @Test
    void suspendAll() {
        final int N = 100;
        Runnable firstRunnable = () -> System.out.print("0 ");
        scheduler.schedule(firstRunnable, 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(() -> System.out.print("1 "), 1, TimeUnit.MILLISECONDS, SleepCalculatorType.IMMEDIATELY);

        sleep(N);

        System.out.println();

        scheduler.suspendAll();

        sleep(2000);

        scheduler.resumeAll();

        sleep(N);

        System.out.println();
        scheduler.stop(firstRunnable);

        sleep(N);

        scheduler.shutDown();
    }

    void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}