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
        scheduler.schedule(() -> System.out.println("HELLO"), 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(() -> System.out.println("GoodBye"), 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutDown();
    }

    @Test
    void fourThreadsSameRunnable(){
        Runnable runnable = () -> System.out.println("HELLO");

        scheduler.schedule(runnable, 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(runnable, 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(runnable, 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(runnable, 1, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutDown();
    }

    @Test
    void reschedule() {
        final int N = 10;
        Runnable firstRunnable = () -> System.out.println("HELLO");
        Runnable secondRunnable = () -> System.out.println("GoodBye");

        scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(secondRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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