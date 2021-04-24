package experis.ds.tests;

import experis.ds.Scheduler;
import experis.ds.SleepCalculatorType;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class EdgeCasesTests {
    final int N = 10;
    final int M = 100;
    Scheduler scheduler = new Scheduler();

    @Test
    void schedule() {
        Runnable firstRunnable = () -> System.out.print("0 ");
        Runnable secondRunnable = () -> System.out.print("1 ");

        System.out.println("print both 0 and 1");

        scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(secondRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.IMMEDIATELY);
        scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.IMMEDIATELY);
        scheduler.schedule(secondRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);

        sleep(M);

        scheduler.stop(firstRunnable);
        System.out.println("\nprint only 1");

        sleep(M);

        scheduler.suspendAll();
        System.out.println("\nprint only 0");
        scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);

        sleep(M);

        scheduler.suspendAll();

        System.out.println("\ndon't print anything (may print one or two commands)");
        sleep(M);

        System.out.println("\nprint only 1");
        scheduler.resume(secondRunnable);

        sleep(M);

        scheduler.resumeAll();
        System.out.println("\nprint both 1 and 2");

        sleep(M);

        scheduler.stop(secondRunnable);
        scheduler.resumeAll();
        scheduler.resume(secondRunnable);

        System.out.println("\nprint only 0");

        sleep(M);

        System.out.println();
        scheduler.schedulesInfo();
        scheduler.shutDown();
        scheduler.schedulesInfo();
    }

    void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}