package experis.ds.particpants;

import java.io.IOException;
import java.io.PrintWriter;

public class ParticipantUserProducer {
    private final ParticipantsNames participants;

    public ParticipantUserProducer(ParticipantsNames participants){
        this.participants = participants;
    }

    public ParticipantUser create(PrintWriter output, String name) throws IOException {
        ParticipantUser participantUser;
        synchronized (participants) {
            name = participants.nameRequest(name);
            participantUser = new ParticipantUser(output, name, new Moderator(), participants);
        }
        return participantUser;
    }
}
