package query.sql;

import entity.Album;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AlbumQueryByArtist {
    private final String sqlPattern = "SELECT * FROM albums WHERE ArtistId in (SELECT ArtistId FROM artists WHERE Name like '%s')";

    public List<Album> execute(Statement stmt, String name) throws SQLException {
        List<Album> albums = new LinkedList<>();
        String sqlTitles = String.format(sqlPattern, name);
        var rs = stmt.executeQuery(sqlTitles);
        while (rs.next()) {
            var album = createAlbum(rs);
            albums.add(album);
        }
        return albums;
    }

    private Album createAlbum(ResultSet rs) throws SQLException {
        int albumId = rs.getInt("AlbumId");
        String title = rs.getString("Title");
        int artistId = rs.getInt("ArtistId");
        return new Album(albumId, title, artistId);
    }
}

