package order;

import entity.Track;
import input.Input;
import output.Display;
import query.TrackFinderById;

import java.sql.SQLException;
import java.util.List;

public class TrackOrder {
    private final PriceCalculator priceCalculator = new PriceCalculator();
    private final TrackFinderById trackFinderById = new TrackFinderById();

    public double order(List<Track> tracks, Display display, Input input) throws SQLException {
        String answer;
        display.print("Enter trackId, type 'done' when you finish");
        while (true) {
            answer = input.getLine();
            if (answer.equalsIgnoreCase("done")) {
                break;
            }

            Track track = trackFinderById.getTrack(tracks, answer);
            if(track != null) {
                priceCalculator.calculate(track);
            }
            else{
                display.print("Track not found");
            }
            display.print("Any thing else?");
        }
        return priceCalculator.getPrice();
    }
}
