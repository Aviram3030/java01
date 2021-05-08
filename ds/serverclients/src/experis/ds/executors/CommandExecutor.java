package experis.ds.executors;

import experis.ds.particpants.ParticipantUser;
import experis.ds.particpants.ParticipantsNames;
import experis.ds.request.other.*;
import experis.ds.rooms.Room;
import experis.ds.commands.CommandType;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor implements ICommandExecutor {
    private final ConcurrentHashMap<CommandType, Request> requests = new ConcurrentHashMap<>();

    public CommandExecutor(HashSet<Room> rooms, ParticipantsNames participants) {
        requests.put(CommandType.ENTER_ROOM, new EnterRoomRequest(rooms));
        requests.put(CommandType.NICK, new NameChangeRequest(participants));
        requests.put(CommandType.MESSAGE_USER, new DirectMessageRequest());
        requests.put(CommandType.REGULAR_MESSAGE, new MessageForRoom());
        requests.put(CommandType.BAN, new BanUserRequest());
    }

    @Override
    public void execute(ParticipantUser participantUser, String msg, CommandType type){
        Request request = requests.get(type);
        request.makeRequest(participantUser, msg);
    }

}
