package experis.ds.particpants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParticipantAdmin extends ParticipantUser{
    private final ServerSocket server;

    public ParticipantAdmin(PrintWriter output, String name, Moderator moderator,
                            ConcurrentHashMap<String, ParticipantUser> participants,
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
            ParticipantUser participantUser = participants.get(name);
            participantUser.close();
        }
    }
}
