package experis.ds.particpants;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

public class ParticipantUserProducer {
    private final ConcurrentHashMap<String, ParticipantUser> participants;

    public ParticipantUserProducer(ConcurrentHashMap<String, ParticipantUser> participants){
        this.participants = participants;
    }

    public ParticipantUser create(PrintWriter output, String name) throws IOException {
        ParticipantUser participantUser;
        synchronized (participants) {
            if (participants.containsKey(name)) {
                name = "Anonymous";
            }
            participantUser = new ParticipantUser(output, name, new Moderator(), participants);
        }
        return participantUser;
    }
}
