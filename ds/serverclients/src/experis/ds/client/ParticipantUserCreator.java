package experis.ds.client;

import experis.ds.particpants.ParticipantUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ParticipantUserCreator {
    private ConcurrentHashMap<String, ParticipantUser> participants;

    public ParticipantUserCreator(ConcurrentHashMap<String, ParticipantUser> participants){
        this.participants = participants;
    }

    public ParticipantUser create(Socket client, Room room) throws IOException {
        var inputStream = new InputStreamReader(client.getInputStream());
        var input = new BufferedReader(inputStream);
        ParticipantUser clientUser;
        String name = input.readLine();
        synchronized (participants) {
            if (participants.containsKey(name)) {
                return null;
            }
            else{
                var output = new PrintWriter(client.getOutputStream(), true);
                clientUser = new ParticipantUser(output, room, name);
                participants.put(name, clientUser);
            }
        }
        return clientUser;
    }
}
