package order;

import entity.Track;
import input.Input;
import output.Display;
import query.TrackFinderById;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class TrackOrder {
    private final PriceCalculator priceCalculator = new PriceCalculator();
    private final TrackFinderById trackFinderById = new TrackFinderById();
    private final HashMap<Track, Integer> quantity = new HashMap<>();

    public double order(List<Track> tracks, Display display, Input input) throws SQLException {
        String answer;
        display.printMessage("Enter trackId, type 'done' when you finish");
        while (true) {
            answer = input.getLine();
            if (answer.equalsIgnoreCase("done")) {
                break;
            }

            Track track = trackFinderById.getTrack(tracks, answer);
            if(track != null) {
                priceCalculator.calculate(track);
                updateMap(track);
            }
            else{
                display.printMessage("Track not found");
            }
            display.printMessage("Any thing else?");
        }
        return priceCalculator.getPrice();
    }

    private void updateMap(Track track){
        Integer num = quantity.get(track);
        if(num == null){
            num = 0;
        }
        quantity.put(track, num + 1);
    }

    public HashMap<Track, Integer> getQuantity(){
        return quantity;
    }

}
