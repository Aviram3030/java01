package experis.ds.userinterface;

import experis.ds.exceptions.InvalidCodeException;
import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.logic.MovieCenter;
import experis.ds.logic.Observer;
import experis.ds.userinterface.input.ConsoleInput;
import experis.ds.userinterface.output.Display;
import experis.ds.userinterface.output.DisplayConsole;
import experis.ds.userinterface.input.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class UserInterface {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private Input input;
    private Display display ;
    private TaskManager taskManager = new TaskManager();
    private List<Observer> observerList = new ArrayList<>();
    private List<Future<Observer>> futures = new ArrayList<>();

    public void start(){
        Scanner reader = new Scanner(System.in);
        ConsoleInput consoleInput = new ConsoleInput(reader);
        DisplayConsole displayConsole = new DisplayConsole();
        boolean present = true;

        while(present){
            inputInterface();
            switch(reader.nextLine()){
                case "1" : {
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
                outputInterface();
                switch(reader.nextLine()){
                    case "1" : {
                        display = displayConsole;
                        break;
                    }
                    default: {
                        present = false;
                        continue;
                    }
                }

                taskManager.checkFuture();
            }
            catch(MovieNotFoundException e){
                e.printStackTrace();
                System.out.println("Try again");
            }
            catch(InvalidCodeException e){
                e.printStackTrace();
                break;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Goodbye");
    }

    private static void inputInterface(){
        System.out.println("type 1 for console input");
        System.out.println("type any to exit");
        System.out.print("> ");
    }

    private static void outputInterface(){
        System.out.println("type 1 for console display");
        System.out.println("type any to exit");
        System.out.print("> ");
    }
}
