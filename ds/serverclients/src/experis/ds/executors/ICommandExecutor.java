package experis.ds.executors;

import experis.ds.particpants.ParticipantUser;
import experis.ds.commands.CommandType;

public interface ICommandExecutor {
    void execute(ParticipantUser participantUser, String msg, CommandType type);
}
