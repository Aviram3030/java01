package experis.ds.userinterface;

import experis.ds.exceptions.InvalidCodeException;
import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.logic.Observer;
import experis.ds.userinterface.input.ConsoleInput;
import experis.ds.userinterface.output.Display;
import experis.ds.userinterface.output.DisplayConsole;
import experis.ds.userinterface.input.Input;
import experis.ds.userinterface.output.DisplayObserverConsole;

import java.util.Scanner;
import java.util.concurrent.*;

public class UserInterface {
    private final TaskManager taskManager = new TaskManager();
    private final Scanner reader = new Scanner(System.in);
    private Input input;
    private Display display;
    private final ConsoleInput consoleInput = new ConsoleInput(reader);
    private final DisplayConsole displayConsole = new DisplayConsole();
    private DisplayObserverConsole displayObserverConsole;
    private boolean present = true;

    public void start() {
        while (present) {
            inputInterface();
            switch (reader.nextLine()) {
                case "1": {
                    input = consoleInput;
                    break;
                }
                default: {
                    present = false;
                    continue;
                }
            }

            System.out.println("Which movie are you looking for?");
            String data = input.getInput();

            try {
                taskManager.execute(data);
                taskManager.checkFuture();
            } catch (MovieNotFoundException e) {
                e.printStackTrace();
                System.out.println("Try again");
            } catch (InvalidCodeException e) {
                e.printStackTrace();
                break;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            userRequestInterface();

            switch (reader.nextLine()) {
                case "1": {
                    stopTask();
                }
                case "2": {
                    getData();
                    if(!present){
                        continue;
                    }
                }
            }
        }

        System.out.println("Goodbye");
        reader.close();
    }


    private void stopTask() {
        System.out.println("Enter the name of query you want to stop");
        taskManager.stop(reader.nextLine());
    }

    private void getData() {
        System.out.println("Enter the name of query you want to get");
        Observer observer = taskManager.getObserver(reader.nextLine());
        System.out.println("Type 1 for console display");
        System.out.println("Type any to exit");
        System.out.print("> ");

        switch (reader.nextLine()) {
            case "1": {
                displayObserverConsole.getOutput(observer);
                break;
            }
            default: {
                present = false;
            }
        }
    }

    private static void inputInterface() {
        System.out.println("Type 1 for console input");
        System.out.println("Type any to exit");
        System.out.print("> ");
    }

    private static void userRequestInterface() {
        System.out.println("Type 1 to stop task");
        System.out.println("Type 2 to get the data for task that has finished");
        System.out.println("Type any to move on");
        System.out.print("> ");
    }
}
