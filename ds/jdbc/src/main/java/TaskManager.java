import QueryCallers.TrackCaller;
import QueryCallers.FirstTrack;
import entity.Customer;
import output.Display;
import input.Input;
import query.sql.SqlConnection;
import query.sql.UserQueryById;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<String, TrackCaller> queryCaller = new HashMap<>();
    private final UserQueryById userQueryById = new UserQueryById();
    private final Statement stmt;
    private Customer customer;

    public TaskManager(SqlConnection sqlConnection, Display display, Input input, String id){
        stmt = sqlConnection.start();
        makeCustomer(id);
        queryCaller.put("1", new FirstTrack(customer, display, input, stmt));
    }

    public void makeCustomer(String id) {
        try {
            customer = userQueryById.execute(stmt, id);
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
