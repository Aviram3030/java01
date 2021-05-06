package experis.ds.request.other;

import experis.ds.particpants.ParticipantUser;

public class MessageForRoom implements Request {
    @Override
    public void makeRequest(ParticipantUser participantUser, String msg) {
        participantUser.sendMessage(msg);
    }
}
