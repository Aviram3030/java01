package ui;

import QueryCallers.TrackCaller;
import QueryCallers.FirstTrack;
import entity.Customer;
import entity.User;
import output.Display;
import input.Input;
import query.sql.SqlConnection;
import query.sql.CustomerQueryById;
import query.sql.UserQueryByID;
import ui.ITaskManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class TaskManager implements ITaskManager {
    private final HashMap<String, TrackCaller> queryCaller = new HashMap<>();
    private final CustomerQueryById customerQueryById = new CustomerQueryById();
    private final UserQueryByID userQueryByID = new UserQueryByID();
    private final Connection connection;
    private Customer customer;
    private User user;

    public TaskManager(SqlConnection sqlConnection, Display display, Input input, String id) throws SQLException {
        connection = sqlConnection.connect();
        makeUser(id);
        makeCustomer(id);
        queryCaller.put("1", new FirstTrack(customer, display, input, connection));
    }

    private void makeUser(String id) throws SQLException {
        user = userQueryByID.execute(connection, id);
    }

    private void makeCustomer(String id) throws SQLException {
        customer = customerQueryById.execute(connection, id);
    }

    public User getUser(){
        return user;
    }

    @Override
    public boolean start(String track){
        TrackCaller caller = queryCaller.get(track);
        if(caller != null){
            caller.startTrack();
            return true;
        }
        return false;
    }
}
