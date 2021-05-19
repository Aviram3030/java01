package query.sql;
import entity.Customer;

import java.sql.*;

public class CustomerQueryById {
    private final String sqlPattern = "SELECT CustomerId, Address, City, Company, State, Country, PostalCode " +
            "FROM customers " +
            "WHERE CustomerId = ?";

    public Customer execute(Connection connection, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlPattern);
        preparedStatement.setString(1, id);
        var rs = preparedStatement.executeQuery();
        return createUserDetails(rs);
    }

    private Customer createUserDetails(ResultSet rs) throws SQLException {
        int customerId = rs.getInt("CustomerId");
        String address = rs.getString("Address");
        String city = rs.getString("City");
        String company = rs.getString("Company");
        String state = rs.getString("State");
        String country = rs.getString("Country");
        String postalCode = rs.getString("PostalCode");

        return new Customer(customerId, city, company, address, state, country, postalCode);
    }
}