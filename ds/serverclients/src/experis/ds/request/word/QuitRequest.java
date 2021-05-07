package experis.ds.request.word;

import experis.ds.particpants.Participant;
import experis.ds.particpants.ParticipantUser;
import experis.ds.request.word.OneWordRequest;

public class QuitRequest implements OneWordRequest {

    @Override
    public void makeRequest(ParticipantUser participantUser) {
        participantUser.close();
    }
}
