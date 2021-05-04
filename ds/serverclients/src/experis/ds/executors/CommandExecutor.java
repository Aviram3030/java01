package experis.ds.executors;

import experis.ds.particpants.ParticipantUser;
import experis.ds.request.*;
import experis.ds.rooms.Room;
import experis.ds.commands.CommandType;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor implements ICommandExecutor {
    private final ConcurrentHashMap<CommandType, Request> requests = new ConcurrentHashMap<>();

    public CommandExecutor(List<Room> rooms, ConcurrentHashMap<String, ParticipantUser> users) {
        requests.put(CommandType.ENTER_ROOM, new EnterRoomRequest(rooms));
        requests.put(CommandType.LEAVE_ROOM, new LeaveRequest());
        requests.put(CommandType.ROOMS_LIST, new RoomListRequest(rooms));
        requests.put(CommandType.NICK, new NameChangeRequest(users));
        requests.put(CommandType.MESSAGE_USER, new DirectMessageRequest(users));
        requests.put(CommandType.REGULAR_MESSAGE, new MessageForRoom());
        requests.put(CommandType.QUIT, new QuitRequest());
    }

    @Override
    public void execute(ParticipantUser participantUser, String msg, CommandType type){
        Request request = requests.get(type);
        request.makeRequest(participantUser, msg);
    }

}
