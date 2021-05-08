package experis.ds.particpants;

import java.util.concurrent.ConcurrentHashMap;

public class ParticipantsNames {
    private final ConcurrentHashMap<String, ParticipantUser> participants = new ConcurrentHashMap<>();

    public String nameRequest (String clientRequestName) {
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
        }
        return clientNewName;
    }

    public void changeName (ParticipantUser participantUser, String clientRequestName) {
        remove(participantUser.getName());
        String clientNewName = nameRequest(clientRequestName);
        participantUser.setName(clientNewName);
        participants.put(clientNewName, participantUser);
    }

    public void remove(String name){
        participants.remove(name);
    }

    public void add(String name, ParticipantUser participantUser){
        participants.put(name, participantUser);
    }

    public ParticipantUser getUser(String name){
        return participants.get(name);
    }
}
