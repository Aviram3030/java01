package experis.ds;

import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.gson.Movie;

import java.util.Scanner;

public class UserInterface {
    public static void start(){
        Scanner reader = new Scanner(System.in);
        var movieCenter = MovieCenter.getInstance();
        System.out.print("Hello, Which movie are you looking for? ");
        while(true){
            System.out.println("\ntype '.' to exit");
            System.out.print("> ");
            String input = reader.nextLine();
            if(input.equals(".")){
                break;
            }
            try {
                Movie[] movies = movieCenter.search(input);
                Display.print(movies);
            }
            catch(MovieNotFoundException e){
                e.printStackTrace();
                System.out.println("Try again");
            }
        }
        System.out.println("Goodbye");
    }
}
