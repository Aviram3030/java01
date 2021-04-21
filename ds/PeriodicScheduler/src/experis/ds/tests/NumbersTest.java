package experis.ds.tests;

import experis.ds.Scheduler;
import experis.ds.SleepCalculatorType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class NumbersTest {
    Scheduler scheduler = new Scheduler();

    @Test
    void calculateListSize(){
        final int N = 50;
        final int M = 500;

        List<Integer> list = new ArrayList<>();
        Runnable runnable = () -> list.add(1);
        scheduler.schedule(runnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        try {
            Thread.sleep(M);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutDown();
        //System.out.println(list.size());
        assertTrue(list.size() > 8 && list.size() < 12);

        scheduler.schedule(() -> list.add(1), N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        try {
            Thread.sleep(M);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(list.size() > 16 && list.size() < 24);
        scheduler.shutDown();
    }

    @Test
    void testStop(){
        final int N = 10;
        final int M = 100;

        AtomicInteger a = new AtomicInteger(0);
        Runnable firstRunnable = () -> a.getAndSet(a.get() + 1);
        Runnable secondRunnable = () -> a.getAndSet(a.get() - 1);

        scheduler.schedule(firstRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        scheduler.schedule(secondRunnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);

        try {
            Thread.sleep(M);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.stop(secondRunnable);
        try {
            Thread.sleep(M);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(a.get() > 0);
        scheduler.shutDown();
    }

    @Test
    void testSuspendAndResume(){
        final int N = 100;
        final int M = 1000;

        List<Integer> list = new ArrayList<>();
        Runnable runnable = () -> list.add(1);
        scheduler.schedule(runnable, N, TimeUnit.MILLISECONDS, SleepCalculatorType.DELAY);
        try {
            Thread.sleep(M);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(list.size() > 8 && list.size() < 12);

        scheduler.suspend(runnable);
        try {
            Thread.sleep(M);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.resume(runnable);

        try {
            Thread.sleep(M);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
        assertTrue(list.size() > 17 && list.size() < 23);
    }
}