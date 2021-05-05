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
        ParticipantUser clientUser;
        synchronized (participants) {
            if (participants.containsKey(name)) {
                return null;
            }
            else{
                clientUser = new ParticipantUser(output, name, new Moderator());
                participants.put(name, clientUser);
            }
        }
        return clientUser;
    }
}
