package experis.ds.request.other;

import experis.ds.particpants.ParticipantUser;

import java.util.concurrent.ConcurrentHashMap;

public class NameChangeRequest implements Request {
    private final ConcurrentHashMap<String, ParticipantUser> participants;

    public NameChangeRequest(ConcurrentHashMap<String, ParticipantUser> participants) {
        this.participants = participants;
    }

    @Override
    public void makeRequest(ParticipantUser participantUser, String msg) {
        if (msg == null) {
            return;
        }
        String clientUsername = participantUser.getName();
        synchronized (participants) {
            participants.remove(clientUsername);
        }
        add(participantUser, msg);
    }

    private void add(ParticipantUser participantUser, String clientRequestName) {
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
            participantUser.setName(clientNewName);
            participants.put(clientNewName, participantUser);
        }
    }
}
