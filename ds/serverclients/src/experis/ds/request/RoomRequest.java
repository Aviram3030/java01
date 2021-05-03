package experis.ds.request;

import experis.ds.client.Room;
import experis.ds.particpants.ParticipantUser;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RoomRequest implements Request {
    private List<Room> rooms;
    private final Lock roomsLock = new ReentrantLock();

    public RoomRequest(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void request(ParticipantUser participantUser, String msg) {
        changeRoom(participantUser, msg);
    }

    public void lobby(ParticipantUser clientUser){
        roomsLock.lock();
        Room room = rooms.get(0);
        roomsLock.unlock();
        clientUser.setRoom(room);
    }

    private void changeRoom(ParticipantUser clientUser, String msg){
        Room room = getRoom(msg);
        if(room == null){
            room = new Room(msg);

            roomsLock.lock();
            rooms.add(room);
            roomsLock.unlock();
        }
        clientUser.setRoom(room);
    }

    private Room getRoom(String msg) {
        roomsLock.lock();
        for(var room: rooms){
            if(room.getName().equals(msg)){
                return room;
            }
        }
        roomsLock.unlock();
        return null;
    }

    public void getRoomsList(ParticipantUser participantUser) {
        StringBuilder sb = new StringBuilder();
        for(var room: rooms) {
            sb.append(room);
            sb.append("\n");
        }
        participantUser.printMessage(sb.toString());
    }
}
