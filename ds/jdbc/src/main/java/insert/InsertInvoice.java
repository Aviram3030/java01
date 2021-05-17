package insert;

import entity.Customer;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertInvoice {
    private final String sqlPattern = "INSERT INTO invoices (CustomerId, InvoiceDate, BillingAddress," +
            " BillingCity, BillingState, BillingCountry, BillingPostalCode, Total)" +
            " VALUES ('%s','%s','%s','%s','%s','%s','%s', %f);";

    public void execute(Statement stmt, Customer customer, double price) throws SQLException {
        String sqlTitles = getSqlTitles(customer, price);
        stmt.executeUpdate(sqlTitles);
    }

    private String getSqlTitles(Customer customer, double price){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String address = customer.getAddress();
        String city = customer.getCity();
        String state = customer.getState();
        String country = customer.getCountry();
        String postalCode =customer.getPostalCode();
        int customerId = customer.getCustomerId();

        return String.format(sqlPattern, customerId, dtf.format(now), address, city, state, country, postalCode, price);
    }

}
