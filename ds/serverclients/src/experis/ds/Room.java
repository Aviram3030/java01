package experis.ds;

import experis.ds.client.ClientUser;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private final List<ClientUser> clients = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public void printInRoom(String msg){
        for(ClientUser client: clients){
            client.printMessage(msg);
        }
    }

    public void addClient(ClientUser clientUser){
        clients.add(clientUser);
    }

    public void removeClient(ClientUser clientUser){
        clients.remove(clientUser);
    }

    public String getName(){
        return name;
    }
}
