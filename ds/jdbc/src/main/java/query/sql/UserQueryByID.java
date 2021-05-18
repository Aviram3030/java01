package query.sql;

import entity.Customer;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQueryByID {
    private final String sqlPattern = "SELECT FirstName, LastName, City, Email " +
            "FROM customers " +
            "WHERE CustomerId = ?";

    public User execute(Connection connection, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlPattern);
        preparedStatement.setString(1, id);
        var rs = preparedStatement.executeQuery();
        return createUserDetails(rs);
    }

    private User createUserDetails(ResultSet rs) throws SQLException {
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String email = rs.getString("Email");
        String city = rs.getString("City");

        return new User(firstName, lastName, email, city);
    }
}
