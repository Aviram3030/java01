package experis.ds.tests;

import experis.ds.Scheduler;
import experis.ds.SleepCalculatorType;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class SuspendAllTest {

    Scheduler scheduler = new Scheduler();

    @Test
    void suspendAll() {
        final int N = 100;
        Runnable firstRunnable = () -> System.out.print("0 ");
        System.out.println("print 1 and 0 : ");
        scheduler.schedule(firstRunnable, 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(() -> System.out.print("1 "), 1, TimeUnit.MILLISECONDS, SleepCalculatorType.IMMEDIATELY);

        sleep(N);

        scheduler.suspendAll();

        System.out.println("\ndon't print anything :");
        sleep(2000);

        System.out.println("print 1 and 0 : ");
        scheduler.resumeAll();

        sleep(N);

        System.out.println("\nprint only 1 :");
        scheduler.stop(firstRunnable);

        sleep(N);

        scheduler.shutDown();
        System.out.println("\nGood bye");
    }

    void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}