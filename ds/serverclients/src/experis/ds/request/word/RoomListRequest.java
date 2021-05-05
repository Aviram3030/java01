package experis.ds.request.word;

import experis.ds.particpants.ParticipantUser;
import experis.ds.request.word.OneWordRequest;
import experis.ds.rooms.Room;

import java.util.List;

public class RoomListRequest implements OneWordRequest {
    private final List<Room> rooms;

    public RoomListRequest(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void makeRequest(ParticipantUser participantUser) {
        StringBuilder sb = new StringBuilder();
        sb.append("Rooms name: ");
        synchronized (rooms) {
            for (var room : rooms) {
                sb.append(room.getName());
                sb.append(", ");
            }
        }
        sb.append("\b\b");
        participantUser.printMessage(sb.toString());
    }
}
