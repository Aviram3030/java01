package callers;

import entity.Album;
import entity.Model;
import order.TrackOrder;
import entity.Customer;
import entity.Track;
import output.Display;
import input.Input;
import query.IQuery;

import java.sql.SQLException;
import java.util.List;

public class FirstTrack implements TrackExecutor {
    private final Customer customer;
    private final IQuery iQuery;
    private final Display display;
    private final Input input;


    public FirstTrack(Customer customer, Display display, Input input, IQuery iQuery) {
        this.customer = customer;
        this.display = display;
        this.input = input;
        this.iQuery = iQuery;
    }

    @Override
    public void startTrack(){
        try {
            var albums = albumQueryByArtist();
            printModels(albums);
            var tracks = albumQueryById();
            printModels(tracks);
            orderTracks(tracks);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Album> albumQueryByArtist() throws SQLException {
        display.printMessage("Select artist full or partial name");
        String query = input.getLine();
        return iQuery.albumQueryByArtist(query);
    }

    private List<Track> albumQueryById() throws SQLException {
        display.printMessage("Select album by id");
        String albumById = input.getLine();
        return iQuery.albumIdQuery(albumById);
    }

    private void orderTracks(List<Track> tracks) throws SQLException {
        display.printMessage("Do you want to order anything from there?");
        display.printMessage("Y for yes");
        String answer = input.getLine();
        if(answer.equalsIgnoreCase("y")){
            TrackOrder trackOrder = new TrackOrder();
            double price = trackOrder.order(tracks, display, input);
            if(price == 0) {
                return;
            }

            display.printMessage("Price: " + price);
            iQuery.insertInvoice(customer, price);
            insertInvoiceItems(trackOrder);
        }
    }

    private void insertInvoiceItems(TrackOrder trackOrder) throws SQLException {
        var quantity = trackOrder.getQuantity();
        int currentId = iQuery.currentTrackIdQuery();
        for (var track: quantity.keySet()) {
            iQuery.insertInvoiceItems(track, currentId, quantity.get(track));
        }
    }

    private <T extends Model> void printModels(List<T> list){
        for (var model: list) {
            display.displayModel(model);
        }
    }
}
