package experis.ds;

public class ImmediateSleepCalculator implements SleepCalculator{
    @Override
    public int calculate(int elapsed, int cycle) {
        return Math.max(0, elapsed - cycle);
    }
}
