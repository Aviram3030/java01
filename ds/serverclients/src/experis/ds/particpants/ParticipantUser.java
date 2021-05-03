package experis.ds.particpants;

import experis.ds.client.Room;

import java.io.IOException;
import java.io.PrintWriter;

public class ParticipantUser implements Participant {
    private String name;
    private final PrintWriter output;
    private Room room;

    public ParticipantUser(PrintWriter output, Room room, String name) throws IOException {
        this.output = output;
        this.room = room;
        this.name = name;
        room.addParticipant(this);
    }

    @Override
    public void sendMessage(String msg){
        room.printInRoom(name + " says: " + msg);
    }

    public void sendMessageToOne(ParticipantUser clientUser, String msg){
        clientUser.printMessage(msg);
    }

    public void printMessage(String msg){
        output.println(msg);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRoom(Room room){
        this.room.removeParticipant(this);
        this.room = room;
        room.addParticipant(this);
    }

    public Room getRoom(){
        return room;
    }
}
