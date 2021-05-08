package experis.ds.particpants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParticipantAdminProducer {
    private final ParticipantsNames participants;
    private final String password = "1234";

    public ParticipantAdminProducer(ParticipantsNames participants) {
        this.participants = participants;
    }

    public ParticipantAdmin create(PrintWriter output, String name, String password, ServerSocket server) throws IOException {
        if (!this.password.equals(password)) {
            return null;
        }

        ParticipantAdmin participantAdmin;
        synchronized (participants) {
            name = participants.nameRequest(name);
            participantAdmin = new ParticipantAdmin(output, name, new Moderator(), participants, server);
        }
        return participantAdmin;
    }
}
