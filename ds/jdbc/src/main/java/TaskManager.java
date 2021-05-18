import QueryCallers.TrackCaller;
import QueryCallers.FirstTrack;
import entity.Customer;
import output.Display;
import input.Input;
import query.sql.SqlConnection;
import query.sql.CustomerQueryById;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<String, TrackCaller> queryCaller = new HashMap<>();
    private final CustomerQueryById customerQueryById = new CustomerQueryById();
    private final Connection connection;
    private Customer customer;

    public TaskManager(SqlConnection sqlConnection, Display display, Input input, String id) throws SQLException {
        connection = sqlConnection.connect();
        makeCustomer(id);
        queryCaller.put("1", new FirstTrack(customer, display, input, connection));
    }

    private void makeCustomer(String id) {
        try {
            customer = customerQueryById.execute(connection, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Customer getCustomer(){
        return customer;
    }

    public boolean start(String track){
        TrackCaller caller = queryCaller.get(track);
        if(caller != null){
            caller.startTrack();
            return true;
        }
        return false;
    }
}
