package experis.ds;

@FunctionalInterface
public interface SleepCalculator {
    long calculate(long elapsed, long cycle);
}
