package experis.ds.request;

import experis.ds.particpants.ParticipantUser;

public class QuitRequest implements Request{
    @Override
    public void makeRequest(ParticipantUser participantUser, String msg) {
        participantUser.close();
    }
}
