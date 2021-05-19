package query.sql;

import entity.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertInvoiceItems {
    private final String sqlPattern = "INSERT INTO invoice_items (InvoiceId, TrackId, UnitPrice, Quantity)" +
            " VALUES (? , ? , ? , ? )";

    public void execute(Connection connection,Track track, int currentInvoiceId, int quantity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlPattern);
        setPreparedStatement(preparedStatement,track, currentInvoiceId, quantity);
    }

    private void setPreparedStatement(PreparedStatement preparedStatement,Track track, int currentInvoiceId,
                                      int quantity) throws SQLException {

        int trackId = track.getTrackId();
        double unitPrice = track.getUnitPrice();

        preparedStatement.setInt(1, currentInvoiceId);
        preparedStatement.setInt(2, trackId);
        preparedStatement.setDouble(3, unitPrice);
        preparedStatement.setInt(4, quantity);
    }
}
