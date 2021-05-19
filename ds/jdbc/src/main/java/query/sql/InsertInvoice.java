package query.sql;

import entity.Customer;

import java.sql.*;
import java.text.SimpleDateFormat;

public class InsertInvoice {
    private final String sqlPattern = "INSERT INTO invoices (CustomerId, InvoiceDate, BillingAddress," +
            " BillingCity, BillingState, BillingCountry, BillingPostalCode, Total)" +
            " VALUES (? , ? , ? , ? , ? , ? , ? , ? )";

    public void execute(Connection connection, Customer customer, double price) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlPattern);
        setPreparedStatement(preparedStatement, customer, price);
    }

    private void setPreparedStatement(PreparedStatement preparedStatement, Customer customer, double price) throws SQLException {
        int customerId = customer.getCustomerId();
        String address = customer.getAddress();
        String city = customer.getCity();
        String state = customer.getState();
        String country = customer.getCountry();
        String postalCode =customer.getPostalCode();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ts = sdf.format(timestamp);

        //Calendar cal = Calendar.getInstance();
        //Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

        preparedStatement.setInt(1, customerId);
        preparedStatement.setString(2, ts);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, city);
        preparedStatement.setString(5, state);
        preparedStatement.setString(6, country);
        preparedStatement.setString(7, postalCode);
        preparedStatement.setDouble(8, price);
    }

}
