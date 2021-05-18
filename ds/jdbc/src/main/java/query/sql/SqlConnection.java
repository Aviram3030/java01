package query.sql;

import java.sql.*;

public class SqlConnection {
    public Connection connect() throws SQLException {
        var url = "jdbc:sqlite:";
        var dbFile = "src/main/resources/chinook.db";
        Connection conn = DriverManager.getConnection(url+dbFile);

        return conn;
    }
}

