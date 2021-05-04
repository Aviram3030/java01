package experis.ds.particpants;

import experis.ds.rooms.Lobby;
import experis.ds.rooms.Room;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ParticipantUser implements Participant {
    private final int LIMIT = 5;
    private String name;
    private final Socket socket;
    private final PrintWriter output;
    private Room room = Lobby.getLobby();
    private Moderator moderator = new Moderator();

    public ParticipantUser(Socket socket, String name) throws IOException {
        this.socket = socket;
        output = new PrintWriter(socket.getOutputStream(), true);
        this.name = name;
        room.addParticipant(this);
    }

    @Override
    public void sendMessage(String msg){
        msg = checkCurses(msg);
        room.printInRoom(name + " says: " + msg);
    }

    public void sendMessageToOne(ParticipantUser clientUser, String msg){
        msg = checkCurses(msg);
        moderator.shouldBeBanned(LIMIT);
        clientUser.printMessage(name + "(private) says: " + msg);
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
        this.room.removeParticipant(this);
        this.room = room;
        room.addParticipant(this);
    }

    public Room getRoom(){
        return room;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
