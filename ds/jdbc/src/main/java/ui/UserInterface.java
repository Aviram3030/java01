package ui;

import entity.User;
import output.ConsoleDisplay;
import input.ConsoleInput;
import query.sql.SqlConnection;

import java.sql.*;
import java.util.Scanner;

public class UserInterface {
    private final ConsoleInput consoleInput;
    private final ConsoleDisplay consoleDisplay = new ConsoleDisplay();
    private final SqlConnection sqlConnection = new SqlConnection();

    public UserInterface(Scanner reader) {
        consoleInput = new ConsoleInput(reader);
    }

    public void start(){
        System.out.println("Hello, insert your id:");
        String id = consoleInput.getLine();
        TaskManager taskManager;

        try {
            taskManager = new TaskManager(sqlConnection, consoleDisplay, consoleInput, id);
        } catch (SQLException throwables) {
            System.out.println("Connection not found");
            return;
        }

        User user = taskManager.getUser();
        if(user == null){
            System.out.println("User not found");
            return;
        }
        consoleDisplay.print(user);

        boolean running = true;
        while(running){
            System.out.println("\nEnter 1 for first track");
            System.out.println("Enter any other word to exit");
            String track = consoleInput.getLine();
            running = taskManager.start(track);
        }
        System.out.println("Good bye");
    }

}



