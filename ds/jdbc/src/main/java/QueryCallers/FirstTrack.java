package QueryCallers;

import entity.Album;
import order.TrackOrder;
import entity.Customer;
import entity.Track;
import output.Display;
import input.Input;
import insert.InsertInvoice;
import query.sql.QuerySql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FirstTrack implements TrackCaller {
    private final Customer customer;
    private final QuerySql querySql = new QuerySql();
    private final InsertInvoice insertInvoice = new InsertInvoice();
    private final TrackOrder trackOrder = new TrackOrder();
    private final Connection connection;
    private final Display display;
    private final Input input;


    public FirstTrack(Customer customer, Display display, Input input, Connection connection) {
        this.customer = customer;
        this.display = display;
        this.input = input;
        this.connection = connection;
    }

    @Override
    public void startTrack(){
        try {
            var albums = albumQueryByArtist();
            display.print(albums);
            var tracks = albumQueryById();
            display.print(tracks);
            orderTracks(tracks, connection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Album> albumQueryByArtist() throws SQLException {
        display.print("Select artist full or partial name");
        String query = input.getLine();
        return querySql.startAlbumQueryByArtist(connection, query);
    }

    private List<Track> albumQueryById() throws SQLException {
        display.print("Select album by id");
        String albumById = input.getLine();
        return querySql.startAlbumIdQuery(connection, albumById);
    }

    private void orderTracks(List<Track> tracks, Connection connection) throws SQLException {
        display.print("Do you want to order anything from there?");
        display.print("Y for yes");
        String answer = input.getLine();
        if(answer.equalsIgnoreCase("y")){
            double price = trackOrder.orderTracks(tracks, display, input);
            if(price != 0) {
                insertInvoice.execute(connection, customer, price);
            }
        }
    }
}
