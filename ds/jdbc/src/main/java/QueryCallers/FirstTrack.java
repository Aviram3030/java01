package QueryCallers;

import order.TrackOrder;
import entity.Customer;
import entity.Track;
import output.Display;
import input.Input;
import insert.InsertInvoice;
import query.sql.QuerySql;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FirstTrack implements TrackCaller {
    private final Customer customer;
    private final QuerySql querySql = new QuerySql();
    private final InsertInvoice insertInvoice = new InsertInvoice();
    private final TrackOrder trackOrder = new TrackOrder();
    private final Statement stmt;
    private final Display display;
    private final Input input;


    public FirstTrack(Customer customer, Display display, Input input, Statement stmt) {
        this.customer = customer;
        this.display = display;
        this.input = input;
        this.stmt = stmt;
    }

    @Override
    public void startTrack(){
        try {
            display.print("Select artist full or partial name");
            String query = input.getLine();
            var albums = querySql.startAlbumQueryByArtist(stmt, query);
            display.print(albums);
            display.print("Select album by id");
            String albumById = input.getLine();
            var tracks = querySql.startAlbumIdQuery(stmt, albumById);
            display.print(tracks);
            orderTracks(tracks, stmt);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void orderTracks(List<Track> tracks, Statement stmt) throws SQLException {
        display.print("Do you want to order anything from there?");
        display.print("Y for yes");
        String answer = input.getLine();
        if(answer.equalsIgnoreCase("y")){
            double price = trackOrder.orderTracks(tracks, display, input);
            if(price != 0) {
                insertInvoice.execute(stmt, customer, price);
            }
        }
    }
}
