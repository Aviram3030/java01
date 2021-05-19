package query.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CurrentTrackIdQuery {
    private final String sqlPattern = "SELECT Max(InvoiceId) as currentId " +
                                      "FROM Invoices ";

    public int execute(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlPattern);
        var rs = preparedStatement.executeQuery();
        return rs.getInt("currentId");
    }



}
