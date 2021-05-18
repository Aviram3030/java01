package insert;

import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertInvoice {
    private final String sqlPattern = "INSERT INTO invoices (CustomerId, InvoiceDate, BillingAddress," +
            " BillingCity, BillingState, BillingCountry, BillingPostalCode, Total)" +
            " VALUES (? , ? , ? , ? , ? , ? , ? , ? )";

    public void execute(Connection connection, Customer customer, double price) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlPattern);
        setPreparedStatement(preparedStatement, customer, price);
        preparedStatement.executeUpdate();
    }

    private void setPreparedStatement(PreparedStatement preparedStatement, Customer customer, double price) throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String address = customer.getAddress();
        String city = customer.getCity();
        String state = customer.getState();
        String country = customer.getCountry();
        String postalCode =customer.getPostalCode();
        int customerId = customer.getCustomerId();

        preparedStatement.setInt(1, customerId);
        preparedStatement.setString(2, dtf.format(now));
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, city);
        preparedStatement.setString(5, state);
        preparedStatement.setString(6, country);
        preparedStatement.setString(7, postalCode);
        preparedStatement.setDouble(8, price);
    }

}
