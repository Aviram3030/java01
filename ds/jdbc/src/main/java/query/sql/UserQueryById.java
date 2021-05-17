package query.sql;
import entity.Customer;

import java.sql.*;

public class UserQueryById{
    private final String sqlPattern = "SELECT * " +
            "FROM customers " +
            "WHERE CustomerId = '%s'";

    public Customer execute(Statement stmt, String id) throws SQLException {
        String sqlTitles = String.format(sqlPattern, id);
        var rs = stmt.executeQuery(sqlTitles);
        Customer userDetails = createUserDetails(rs);
        return userDetails;
    }

    private Customer createUserDetails(ResultSet rs) throws SQLException {
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
        int customerId = rs.getInt("CustomerId");

        return new Customer(customerId, firstName, lastName, email, city, company, address, state, country, postalCOde,
                phone, fax, supportRepId);
    }
}
