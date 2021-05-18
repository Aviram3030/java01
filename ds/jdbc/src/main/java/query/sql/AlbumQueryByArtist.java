package query.sql;

import entity.Album;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AlbumQueryByArtist {
    private final String sqlPattern = "SELECT * FROM albums" +
            " WHERE ArtistId" +
            " in (SELECT ArtistId FROM artists WHERE Name like ?)";

    public List<Album> execute(Connection connection, String query) throws SQLException {
        PreparedStatement prepareStatement = connection.prepareStatement(sqlPattern);
        prepareStatement.setString(1, "%" + query + "%");

        List<Album> albums = new LinkedList<>();
        var rs = prepareStatement.executeQuery();
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

