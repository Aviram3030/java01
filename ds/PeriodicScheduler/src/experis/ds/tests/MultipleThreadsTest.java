package experis.ds.tests;

import experis.ds.Scheduler;
import experis.ds.SleepCalculatorType;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class MultipleThreadsTest {
    final int N = 10;
    final int M = 100;

    Scheduler scheduler = new Scheduler();
    @Test
    void multipleThreads() {
        Runnable firstRunnable = () -> System.out.print("0 ");
        Runnable secondRunnable = () -> System.out.print("1 ");

        System.out.println("print 0 and 1: ");
        for(int i = 0; i < M; i++){
            scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
            scheduler.schedule(secondRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.IMMEDIATELY);
        }

        sleep(M);
        scheduler.suspendAll();
        System.out.println("\ndon't print anything: ");

        sleep(M);
        System.out.println("\nprint only 0: ");
        scheduler.resume(firstRunnable);

        sleep(M);
        scheduler.resumeAll();
        System.out.println("\nprint 0 and 1: ");

        sleep(M);
        scheduler.suspendAll();

        scheduler.schedulesInfo();
        scheduler.shutDown();
        System.out.println("\nnew scheduler info:(empty) ");
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