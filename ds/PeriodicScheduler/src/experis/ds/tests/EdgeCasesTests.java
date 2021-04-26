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
    void edgeCasesSchedule() {
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
        System.out.println("\nprint both 1 and 0");

        sleep(M);

        scheduler.stop(secondRunnable);
        scheduler.resumeAll();
        scheduler.resume(secondRunnable);

        System.out.println("\nprint only 0");

        sleep(M);

        System.out.println();
        scheduler.schedulesInfo();

        scheduler.shutDown();
        System.out.println("\nShutdown");
        scheduler.schedulesInfo();
    }

    @Test
    void testsWithExceptions() {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] b = new int[]{6, 7, 8, 9, 10};
        Runnable firstRunnable = () -> System.out.print(a[(int) (Math.random() * N)]+ " ");
        Runnable secondRunnable = () -> System.out.print(b[(int) (Math.random() * N)]+ " ");

        scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(secondRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.IMMEDIATELY);

        sleep(M);

        scheduler.suspendAll();
        System.out.println();
        scheduler.schedulesInfo();
    }

    @Test
    void checkNull(){
        Runnable firstRunnable = () -> System.out.print("0 ");
        scheduler.schedule(null, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(firstRunnable, N, null, SleepCalculatorType.DELAY);

        System.out.println("print nothing: ");
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