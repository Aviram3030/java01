package experis.ds.executors;

import experis.ds.particpants.ParticipantUser;
import experis.ds.commands.CommandType;

public interface ICommandExecutor {
    void execute(ParticipantUser clientUser, String msg, CommandType type);
}
