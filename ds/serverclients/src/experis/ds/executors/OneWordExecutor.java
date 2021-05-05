package experis.ds.executors;

import experis.ds.commands.CommandType;
import experis.ds.particpants.ParticipantUser;
import experis.ds.request.*;
import experis.ds.rooms.Room;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class OneWordExecutor implements ICommandExecutor{
    private final ConcurrentHashMap<CommandType, OneWordRequest> requests = new ConcurrentHashMap<>();

    public OneWordExecutor(List<Room> rooms) {
        requests.put(CommandType.LEAVE_ROOM, new LeaveRequest());
        requests.put(CommandType.ROOMS_LIST, new RoomListRequest(rooms));
        requests.put(CommandType.QUIT, new QuitRequest());
    }
    @Override
    public void execute(ParticipantUser participantUser, String msg, CommandType type) {
        OneWordRequest oneWordRequest = requests.get(type);
        oneWordRequest.makeRequest(participantUser);
    }
}
