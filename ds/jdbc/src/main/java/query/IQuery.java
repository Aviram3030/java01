package query;

import entity.Album;
import entity.Customer;
import entity.Track;

import java.sql.SQLException;
import java.util.List;

public interface IQuery {
    List<Track> albumIdQuery(String data) throws SQLException;

    List<Album> albumQueryByArtist(String data) throws SQLException;

    int currentTrackIdQuery() throws SQLException;

    void insertInvoice(Customer customer, double price) throws SQLException;

    void insertInvoiceItems(Track track, int currentId, int quantity) throws SQLException;
}
