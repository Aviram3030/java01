package experis.ds.particpants;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

public class ParticipantUserCreator {
    private final ConcurrentHashMap<String, ParticipantUser> participants;

    public ParticipantUserCreator(ConcurrentHashMap<String, ParticipantUser> participants){
        this.participants = participants;
    }

    public ParticipantUser create(PrintWriter output, String name) throws IOException {
        ParticipantUser participantUser;
        synchronized (participants) {
            if (participants.containsKey(name)) {
                name = "Anonymous";
            }
            participantUser = new ParticipantUser(output, name, new Moderator());
            participants.put(name, participantUser);
        }
        return participantUser;
    }
}
