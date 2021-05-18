import ui.UserInterface;

import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new Scanner(System.in));
        userInterface.start();
    }
}