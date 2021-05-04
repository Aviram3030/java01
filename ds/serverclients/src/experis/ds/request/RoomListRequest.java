package experis.ds.request;

import experis.ds.particpants.ParticipantUser;
import experis.ds.rooms.Room;

import java.util.List;

public class RoomListRequest implements Request{
    private final List<Room> rooms;

    public RoomListRequest(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void makeRequest(ParticipantUser participantUser, String msg) {
        StringBuilder sb = new StringBuilder();
        for(var room: rooms) {
            sb.append(room.getName());
            sb.append("\t");
        }
        participantUser.printMessage(sb.toString());
    }
}
