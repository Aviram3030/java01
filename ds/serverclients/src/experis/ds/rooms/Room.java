package experis.ds.rooms;

import experis.ds.particpants.ParticipantUser;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String name;
    private final List<ParticipantUser> participants = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public synchronized void printInRoom(String msg){
        for(ParticipantUser client: participants){
            client.printMessage(msg);
        }
    }

    public synchronized void addParticipant(ParticipantUser ParticipantUser){
        participants.add(ParticipantUser);
    }

    public synchronized void removeParticipant(ParticipantUser ParticipantUser){
        participants.remove(ParticipantUser);
    }

    public String getName(){
        return name;
    }

}
