package experis.ds.client;

import experis.ds.particpants.ParticipantUser;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String name;
    private final List<ParticipantUser> clients = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public void printInRoom(String msg){
        for(ParticipantUser client: clients){
            client.printMessage(msg);
        }
    }

    public void addParticipant(ParticipantUser ParticipantUser){
        clients.add(ParticipantUser);
    }

    public void removeParticipant(ParticipantUser ParticipantUser){
        clients.remove(ParticipantUser);
    }

    public String getName(){
        return name;
    }

}
