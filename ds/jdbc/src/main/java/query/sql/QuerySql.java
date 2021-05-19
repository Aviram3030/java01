package query.sql;

import entity.Album;
import entity.Customer;
import entity.Track;
import query.IQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuerySql implements IQuery {
    private final Connection connection;
    private final TracksQueryByAlbumId tracksQueryByAlbumId = new TracksQueryByAlbumId();
    private final AlbumQueryByArtist albumQueryByArtist = new AlbumQueryByArtist();
    private final CurrentTrackIdQuery currentTrackIdQuery = new CurrentTrackIdQuery();
    private final InsertInvoice insertInvoice = new InsertInvoice();
    private final InsertInvoiceItems insertInvoiceItems = new InsertInvoiceItems();

    public QuerySql(Connection connection){
        this.connection = connection;
    }

    public List<Track> albumIdQuery(String data) throws SQLException {
        return tracksQueryByAlbumId.execute(connection, data);
    }

    public List<Album> albumQueryByArtist(String data) throws SQLException {
        return albumQueryByArtist.execute(connection, data);
    }

    public int currentTrackIdQuery() throws SQLException {
        return currentTrackIdQuery.execute(connection);
    }

    public void insertInvoice(Customer customer, double price) throws SQLException {
       insertInvoice.execute(connection, customer, price);
    }

    public void insertInvoiceItems(Track track, int currentId, int quantity) throws SQLException {
        insertInvoiceItems.execute(connection, track, currentId, quantity);
    }

}
