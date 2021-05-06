package experis.ds.particpants;

import experis.ds.rooms.Lobby;
import experis.ds.rooms.Room;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ParticipantUser implements Participant {
    private final int LIMIT = 5;
    private String name;
    private final PrintWriter output;
    private Room room = Lobby.getLobby();
    private final IModerator moderator;
    private boolean alive = true;
    private final Date date = new Date();

    public ParticipantUser(PrintWriter output, String name, Moderator moderator) throws IOException {
        this.moderator = moderator;
        this.output = output;
        this.name = name;
        room.addParticipant(this);
    }

    @Override
    public void sendMessage(String msg){
        msg = checkCurses(msg);
        room.printInRoom("[" + date.toString() + "] "+ name + " says: " + msg);
    }

    public void sendMessageToOne(ParticipantUser participantUser, String msg){
        msg = checkCurses(msg);
        moderator.shouldBeBanned(LIMIT);
        participantUser.printMessage("[" + date.toString() + "] "+ name + "(private) says: " + msg);
    }

    private String checkCurses(String msg) {
        msg = moderator.getWithoutCurses(msg);
        if(moderator.shouldBeBanned(LIMIT)){
            close();
        }
        return msg;
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
        synchronized (room) {
            this.room.removeParticipant(this);
            this.room = room;
            room.addParticipant(this);
        }
        output.println("You have entered the room: " + room.getName());
    }

    public Room getRoom(){
        return room;
    }

    public void close() {
        alive = false;
    }

    public boolean isAlive(){
        return alive;
    }
}
