package experis.ds.request.other;

import experis.ds.particpants.Participant;
import experis.ds.rooms.Room;
import experis.ds.particpants.ParticipantUser;

import java.util.List;

public class EnterRoomRequest implements Request {
    private final List<Room> rooms;

    public EnterRoomRequest(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void makeRequest(ParticipantUser participantUser, String msg) {
        changeRoom(participantUser, msg);
    }

    private void changeRoom(ParticipantUser participantUser, String msg){
        synchronized (rooms) {
            Room room = getRoom(msg);
            if (room == null) {
                room = new Room(msg);
                rooms.add(room);
            }
            participantUser.setRoom(room);
        }
    }

    private Room getRoom(String msg) {
        for(var room: rooms){
            if(room.getName().equals(msg)){
                return room;
            }
        };
        return null;
    }

}
