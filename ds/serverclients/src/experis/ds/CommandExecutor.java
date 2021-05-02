package experis.ds;

import experis.ds.client.ClientUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommandExecutor implements ICommandExecutor {
    private Lock namesLock = new ReentrantLock();
    private Lock roomsLock = new ReentrantLock();
    private List<String> names = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();

    @Override
    public void execute(ClientUser clientUser, String msg) {
        if (msg.startsWith("nick")) {
            msg = getSecondWord(msg);
            changeNick(clientUser, msg);
        }
        else if (msg.startsWith("room")){
            msg = getSecondWord(msg);
            changeRoom(clientUser, msg);
        }
        else if(msg.startsWith("msg")){
            //TODO: direct message
        }
        else{
            clientUser.sendMessage(msg);
        }
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

    private void changeNick(ClientUser clientUser, String msg){
        if(!names.contains("msg")) {
            clientUser.setName(msg);
            namesLock.lock();
            names.remove(clientUser.getName());
            names.add(msg);
            namesLock.unlock();
        }
    }

    private void changeRoom(ClientUser clientUser, String msg){
        Room room = getRoom(msg);
        if(room == null){
            room = new Room(msg);
            roomsLock.lock();
            rooms.add(room);
            roomsLock.unlock();
        }
        clientUser.setRoom(room);
    }

    private String getSecondWord(String msg){
        int pos = msg.indexOf(" ");
        return msg.substring(pos);
    }
}
