package experis.ds;

import experis.ds.lmbda.SleepCalculator;
import experis.ds.lmbda.TaskRunner;
import experis.ds.lmbda.StatusChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Scheduler {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private final ConcurrentHashMap<Runnable, List<Task>> tasksExtractor = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Task, ForkJoinTask<Void>> futureExtractor = new ConcurrentHashMap<>();

    public void schedule(Runnable operation, long period, TimeUnit timeUnit, SleepCalculatorType sleepCalculatorType) {
        if (isNull(operation) || isNull(sleepCalculatorType)) {
            return;
        }
        var sleepCalculator = getSleepCalculator(sleepCalculatorType);
        schedule(operation, period, timeUnit, sleepCalculator);
    }

    public void schedule(Runnable operation, long period, TimeUnit timeUnit, SleepCalculator sleepCalculator) {
        if (checkIsIllegal(operation, period, timeUnit, sleepCalculator)) {
            return;
        }
        var tasks = tasksExtractor.get(operation);
        if (isNull(tasks)) {
            tasks = new ArrayList<>();
            tasksExtractor.put(operation, tasks);
        }
        Task task = new Task(operation, sleepCalculator, timeUnit.toNanos(period));

        ForkJoinTask<Void> future = forkJoinPool.submit(task);
        futureExtractor.put(task, future);
        tasks.add(task);
    }

    private Boolean checkIsIllegal(Runnable operation, long period, TimeUnit timeUnit, SleepCalculator sleepCalculator){
        return (isNull(operation) || isNull(sleepCalculator) || isNull(timeUnit) || period < 0);
    }

    private SleepCalculator getSleepCalculator(SleepCalculatorType sleepCalculatorType){
        if(sleepCalculatorType == SleepCalculatorType.DELAY){
            return (long cycle, long elapsed) -> {
                long num = (cycle - elapsed) % cycle;
                if(num > 0) {
                    return num;
                }
                return num + cycle;
            };
        }
        return (long cycle, long elapsed) -> Math.max(0, cycle - elapsed);
    }

    public void shutDown() {
        for (var operation : tasksExtractor.keySet()) {
            stop(operation);
        }
    }

    public void stop(Runnable operation) {
        List<Task> tasks = getTasks(operation);
        if(isNull(tasks)){
            return;
        }
        for (var task : tasks) {
            if (task.isFinished()) {
                continue;
            }
            task.stop();
            try {
                futureExtractor.get(task).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            futureExtractor.remove(task);
        }
        tasksExtractor.remove(operation);
    }

    public void suspendAll(){
        actionAll(this::suspend);
    }

    public void suspend(Runnable operation) {
        StatusChecker<Task> stateChecker = (Task task) -> !task.isRunning();
        TaskRunner<Task> stateChanger = Task::suspend;
        action(operation, stateChecker, stateChanger);
    }

    public void resumeAll(){
        actionAll(this::resume);
    }

    public void resume(Runnable operation) {
        StatusChecker<Task> stateChecker = (Task task) -> !task.isSuspended();
        TaskRunner<Task> stateChanger = Task::resume;
        action(operation, stateChecker, stateChanger);
    }

    public void schedulesInfo(){
        actionAll(this::scheduleInfo);
    }

    private void scheduleInfo(Runnable operation){
        StatusChecker<Task> stateChecker = (Task task) -> false;
        TaskRunner<Task> stateChanger = Task::printScheduleInfo;
        action(operation, stateChecker, stateChanger);
    }

    private void actionAll(TaskRunner<Runnable> taskRunner){
        for (var task : tasksExtractor.keySet()) {
            taskRunner.apply(task);
        }
    }

    private void action(Runnable operation, StatusChecker<Task> stateChecker, TaskRunner<Task> stateChanger){
        if(isNull(operation)){
            return;
        }

        List<Task> tasks = getTasks(operation);
        if(isNull(tasks)){
            return;
        }
        for (var task : tasks) {
            if (stateChecker.apply(task)) {
                continue;
            }
            stateChanger.apply(task);
        }
    }

    public void reschedule(Runnable operation, long period, TimeUnit timeUnit) {
        List<Task> tasks = getTasks(operation);
        if(isNull(tasks) || period < 0){
            return;
        }
        for (var task : tasks) {
            task.setTime(timeUnit.toNanos(period));
        }
    }

    private List<Task> getTasks(Runnable operation){
        if(isNull(operation)){
            return null;
        }
        return tasksExtractor.get(operation);
    }

    private <T> Boolean isNull(T data){
        return data == null;
    }
}

