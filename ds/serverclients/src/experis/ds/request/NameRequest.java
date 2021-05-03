package experis.ds.request;

import experis.ds.particpants.ParticipantUser;
import java.util.concurrent.ConcurrentHashMap;

public class NameRequest implements Request {
    private final ConcurrentHashMap<String, ParticipantUser> participants;

    public NameRequest(ConcurrentHashMap<String, ParticipantUser> participants) {
        this.participants = participants;
    }

    @Override
    public void request(ParticipantUser participantUser, String msg) {
        if (msg == null) {
            return;
        }
        String clientUsername = participantUser.getName();
        synchronized (participants) {
            participants.remove(clientUsername);
        }
        add(participantUser, msg);
    }

    public void add(ParticipantUser clientUser, String clientRequestName) {
        String clientNewName = clientRequestName;
        int i = 2;
        synchronized (participants) {
            while (participants.get(clientNewName) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(clientRequestName);
                sb.append(i);
                clientNewName = sb.toString();
                i++;
            }
            clientUser.setName(clientNewName);
            participants.put(clientNewName, clientUser);
        }
    }
}
