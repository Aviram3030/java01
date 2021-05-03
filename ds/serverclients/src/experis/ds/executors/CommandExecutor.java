package experis.ds.executors;

import experis.ds.particpants.ParticipantUser;
import experis.ds.client.Room;
import experis.ds.commands.CommandType;
import experis.ds.request.DirectMessageRequest;
import experis.ds.request.NameRequest;
import experis.ds.request.RoomRequest;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor implements ICommandExecutor {
    private final RoomRequest roomRequest;
    private final DirectMessageRequest directMessageRequest;
    private final NameRequest nameRequest;

    public CommandExecutor(List<Room> rooms, ConcurrentHashMap<String, ParticipantUser> users) {
        roomRequest = new RoomRequest(rooms);
        directMessageRequest = new DirectMessageRequest(users);
        nameRequest = new NameRequest(users);
    }

    @Override
    public void execute(ParticipantUser participantUser, String msg, CommandType type){
        switch(type){
            case QUIT -> {
            }
            case ENTER_ROOM -> {
                roomRequest.request(participantUser, getWithoutFirstWord(msg));
            }
            case LEAVE_ROOM -> {
                roomRequest.lobby(participantUser);
            }
            case ROOMS_LIST -> {
                roomRequest.getRoomsList(participantUser);
            }
            case NICK -> {
                nameRequest.request(participantUser, getWithoutFirstWord(msg));
            }
            case MESSAGE_USER -> {
                directMessageRequest.request(participantUser, getWithoutFirstWord(msg));
            }
            default -> {
                participantUser.sendMessage(msg);
            }
        }
    }

    private String getWithoutFirstWord(String msg){
        int pos = msg.indexOf(' ');
        return msg.substring(pos);
    }
}
