package experis.ds.particpants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParticipantAdminProducer {
    private final ConcurrentHashMap<String, ParticipantUser> participants;
    private final String password = "1234";

    public ParticipantAdminProducer(ConcurrentHashMap<String, ParticipantUser> participants) {
        this.participants = participants;
    }

    public ParticipantAdmin create(PrintWriter output, String name, String password, ServerSocket server) throws IOException {
        if (!this.password.equals(password)) {
            return null;
        }

        ParticipantAdmin participantAdmin;
        synchronized (participants) {
            if (participants.containsKey(name)) {
                name = "AnonymousAdmin";
            }
            participantAdmin = new ParticipantAdmin(output, name, new Moderator(), participants, server);
        }
        return participantAdmin;
    }
}
