package experis.ds.request;

import experis.ds.particpants.ParticipantUser;

public class QuitRequest implements OneWordRequest{

    @Override
    public void makeRequest(ParticipantUser participantUser) {
        participantUser.close();
    }
}
