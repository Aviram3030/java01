package experis.ds;

public class DelayedSleepCalculator implements SleepCalculator{

    @Override
    public int calculate(int elapsed, int cycle) {
        return (cycle - elapsed) % cycle;
    }
}
