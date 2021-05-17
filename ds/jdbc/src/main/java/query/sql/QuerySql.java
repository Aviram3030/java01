package query.sql;

import entity.Album;
import entity.Track;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class QuerySql {
    private final TrackQueryByAlbumId trackQueryByAlbumId = new TrackQueryByAlbumId();
    private final AlbumQueryByArtist albumQueryByArtist = new AlbumQueryByArtist();

    public List<Track> startAlbumIdQuery(Statement stmt, String data) throws SQLException {
        return trackQueryByAlbumId.execute(stmt, data);
    }

    public List<Album> startAlbumQueryByArtist(Statement stmt, String data) throws SQLException {
        return albumQueryByArtist.execute(stmt, data);
    }

}
