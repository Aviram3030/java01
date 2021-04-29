package experis.ds.userinterface;

import experis.ds.exceptions.InvalidCodeException;
import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.logic.Observer;
import experis.ds.userinterface.input.ConsoleInput;
import experis.ds.userinterface.input.Input;
import experis.ds.userinterface.output.DisplayObserverConsole;

import java.util.Scanner;
import java.util.concurrent.*;

public class UserInterface {
    private final TaskManager taskManager = new TaskManager();
    private final Scanner reader = new Scanner(System.in);
    private Input input;
    private final ConsoleInput consoleInput = new ConsoleInput(reader);
    private DisplayObserverConsole displayObserverConsole;
    private boolean present = true;

    public void start() {
        while (present) {
            inputInterface();
            if(!present){
                continue;
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
        }

        System.out.println("Goodbye");
        reader.close();
    }

    private void inputInterface() {
        System.out.println("Type 1 for console input");
        System.out.println("Type any to exit");
        System.out.print("> ");
        if ("1".equals(reader.nextLine())) {
            input = consoleInput;
        } else {
            present = false;
        }
    }

    private void userRequestInterface() {
        System.out.println("Type 1 to stop task");
        System.out.println("Type 2 to get the data for task that has finished");
        System.out.println("Type any to move on");
        System.out.print("> ");
        switch (reader.nextLine()) {
            case "1": {
                stopTask();
            }
            case "2": {
                getData();
            }
        }
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

        if ("1".equals(reader.nextLine())) {
            displayObserverConsole.getOutput(observer);
        } else {
            present = false;
        }
    }

}
