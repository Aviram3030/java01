package experis.ds.request;

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

    private synchronized void  changeRoom(ParticipantUser clientUser, String msg){

        Room room = getRoom(msg);
        if(room == null){
            room = new Room(msg);
            rooms.add(room);
        }
        clientUser.setRoom(room);
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
