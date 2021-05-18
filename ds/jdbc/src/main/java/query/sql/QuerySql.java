package query.sql;

import entity.Album;
import entity.Track;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuerySql {
    private final TracksQueryByAlbumId tracksQueryByAlbumId = new TracksQueryByAlbumId();
    private final AlbumQueryByArtist albumQueryByArtist = new AlbumQueryByArtist();

    public List<Track> startAlbumIdQuery(Connection connection, String data) throws SQLException {
        return tracksQueryByAlbumId.execute(connection, data);
    }

    public List<Album> startAlbumQueryByArtist(Connection connection, String data) throws SQLException {
        return albumQueryByArtist.execute(connection, data);
    }

}
