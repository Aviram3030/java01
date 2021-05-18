package query.sql;
import entity.Customer;

import java.sql.*;

public class CustomerQueryById {
    private final String sqlPattern = "SELECT * " +
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
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String email = rs.getString("Email");
        String address = rs.getString("Address");
        String city = rs.getString("City");
        String company = rs.getString("Company");
        String state = rs.getString("State");
        String country = rs.getString("Country");
        String postalCOde = rs.getString("PostalCode");
        String phone = rs.getString("Phone");
        String fax = rs.getString("Fax");
        String supportRepId = rs.getString("SupportRepId");

        return new Customer(customerId, firstName, lastName, email, city, company, address, state, country, postalCOde,
                phone, fax, supportRepId);
    }
}
