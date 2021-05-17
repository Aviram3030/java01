package query.sql;

import java.sql.*;

public class SqlConnection {
    public Statement start(){
        try{
             var conn = connect();
             return conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Connection connect() throws SQLException {
        var url = "jdbc:sqlite:";
        var dbFile = "src/main/resources/chinook.db";
        Connection conn = DriverManager.getConnection(url+dbFile);

        return conn;
    }



}

