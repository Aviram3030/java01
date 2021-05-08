package experis.ds.request.other;

import experis.ds.particpants.ParticipantUser;
import experis.ds.particpants.ParticipantsNames;


public class NameChangeRequest implements Request {
    private final ParticipantsNames participants;

    public NameChangeRequest(ParticipantsNames participants) {
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
        participants.changeName(participantUser, msg);
    }
}
