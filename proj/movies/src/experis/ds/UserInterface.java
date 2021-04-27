package experis.ds;

import experis.ds.exceptions.InvalidCodeException;
import experis.ds.exceptions.MovieNotFoundException;
import experis.ds.gson.Movie;

import java.util.Scanner;

public class UserInterface {
    public static void start(){
        Scanner reader = new Scanner(System.in);
        Input input = new ScannerInput(reader);
        Output output = new Display();
        var movieCenter = MovieCenter.getMovieCenter();

        System.out.print("Hello, Which movie are you looking for? ");
        while(true){
            System.out.println("\ntype '.' to exit");
            System.out.print("> ");
            String data = input.getData();
            if(data.equals(".")){
                break;
            }

            try {
                Movie[] movies = movieCenter.search(data);
                output.getOutput(movies);
            }
            catch(MovieNotFoundException e){
                e.printStackTrace();
                System.out.println("Try again");
            }
            catch(InvalidCodeException e){
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Goodbye");
    }
}
