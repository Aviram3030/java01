package experis.ds;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private final List<ClientInfo> clients = new ArrayList<>();

    public Room() {

    }

    public void printInRoom(String msg){
        for(ClientInfo client: clients){
            client.printMessage(msg);
        }
    }
}
