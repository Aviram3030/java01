package experis.ds.tests;

import experis.ds.Scheduler;
import experis.ds.SleepCalculatorType;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class PrintTest {

    Scheduler scheduler = new Scheduler();

    @Test
    void twoThreadsDifferentRunnable() {
        scheduler.schedule(() -> System.out.print("0 "), 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(() -> System.out.print("1 "), 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutDown();
    }

    @Test
    void fourThreadsSameRunnable(){
        Runnable runnable = () -> System.out.println("0 ");

        scheduler.schedule(runnable, 1, TimeUnit.SECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(runnable, 1, TimeUnit.SECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(runnable, 1, TimeUnit.SECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(runnable, 1, TimeUnit.SECONDS, SleepCalculatorType.DELAY);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutDown();
    }

    @Test
    void reschedule() {
        final int N = 10;
        Runnable firstRunnable = () -> System.out.print("0 ");
        Runnable secondRunnable = () -> System.out.print("1 ");

        scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(secondRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n0 goes faster");

        scheduler.reschedule(firstRunnable, 2, TimeUnit.MILLISECONDS);
        scheduler.reschedule(secondRunnable, N, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutDown();
    }
}