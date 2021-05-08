package experis.ds.executorcaller;

import experis.ds.commands.CommandType;
import experis.ds.executors.CommandExecutor;
import experis.ds.executors.ICommandExecutor;
import experis.ds.executors.OneWordExecutor;
import experis.ds.particpants.Participant;
import experis.ds.particpants.ParticipantUser;
import experis.ds.particpants.ParticipantsNames;
import experis.ds.rooms.Room;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ExecutorCaller {
    private final ConcurrentHashMap<CommandType, ICommandExecutor> executor = new ConcurrentHashMap<>();

    public ExecutorCaller(HashSet<Room> rooms, ParticipantsNames participants){
        fillCommandExecutor(new CommandExecutor(rooms, participants));
        fillOneWordCommandExecutor(new OneWordExecutor(rooms));
    }

    private void fillCommandExecutor(CommandExecutor commandExecutor){
        executor.put(CommandType.NICK, commandExecutor);
        executor.put(CommandType.MESSAGE_USER, commandExecutor);
        executor.put(CommandType.ENTER_ROOM, commandExecutor);
        executor.put(CommandType.REGULAR_MESSAGE, commandExecutor);
        executor.put(CommandType.BAN, commandExecutor);
    }

    private void fillOneWordCommandExecutor(OneWordExecutor oneWordExecutor){
        executor.put(CommandType.QUIT, oneWordExecutor);
        executor.put(CommandType.LEAVE_ROOM, oneWordExecutor);
        executor.put(CommandType.ROOMS_LIST, oneWordExecutor);
        executor.put(CommandType.SHUT_DOWN, oneWordExecutor);
    }

    public void execute(ParticipantUser participantUser, String msg, CommandType type){
        ICommandExecutor commandExecutor = executor.get(type);
        commandExecutor.execute(participantUser, msg, type);
    }

}
