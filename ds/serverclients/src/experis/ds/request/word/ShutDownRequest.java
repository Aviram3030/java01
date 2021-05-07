package experis.ds.request.word;

import experis.ds.particpants.ParticipantAdmin;
import experis.ds.particpants.ParticipantUser;

public class ShutDownRequest implements OneWordRequest{
    @Override
    public void makeRequest(ParticipantUser participantUser) {
        if(participantUser instanceof ParticipantAdmin){
           ((ParticipantAdmin) participantUser).serverShutDown();
           return;
        }
    }
}
