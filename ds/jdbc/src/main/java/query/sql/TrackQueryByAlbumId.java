package query.sql;

import entity.Track;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TrackQueryByAlbumId {
    private final String sqlPattern = "SELECT * FROM tracks WHERE AlbumId = '%s'";

    public List<Track> execute(Statement stmt, String query) throws SQLException {
        List<Track> tracks = new LinkedList<>();
        String sqlTitles = String.format(sqlPattern, query);
        var rs = stmt.executeQuery(sqlTitles);
        while (rs.next()) {
            var track = getTrack(rs);
            tracks.add(track);
        }
        return tracks;
    }

    private Track getTrack(ResultSet rs) throws SQLException {
        int trackId = rs.getInt("TrackId");
        String name = rs.getString("Name");
        int albumId = rs.getInt("AlbumId");
        int mediaTypeId = rs.getInt("MediaTypeId");;
        int genreId = rs.getInt("GenreId");;
        String composer = rs.getString("Composer");
        long milliSeconds = rs.getLong("MilliSeconds");
        long bytes = rs.getLong("MilliSeconds");;
        double unitPrice = rs.getDouble("UnitPrice");

        return new Track(trackId, name, albumId, mediaTypeId, genreId, composer, milliSeconds, bytes, unitPrice);
    }

}
