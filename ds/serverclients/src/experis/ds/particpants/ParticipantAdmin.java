package experis.ds.particpants;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;

public class ParticipantAdmin extends ParticipantUser{
    private final ServerSocket server;

    public ParticipantAdmin(PrintWriter output, String name, Moderator moderator,
                            ParticipantsNames participants,
                            ServerSocket server) throws IOException {
        super(output, name, moderator, participants);
        this.server = server;
    }

    public void serverShutDown() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void banUser(String name){
        synchronized (participants) {
            ParticipantUser participantUser = participants.getUser(name);
            participantUser.close();
        }
    }
}
