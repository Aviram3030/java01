package experis.ds.request.other;

import experis.ds.particpants.ParticipantAdmin;
import experis.ds.particpants.ParticipantUser;

public class BanUserRequest implements Request{
    @Override
    public void makeRequest(ParticipantUser participantUser, String name) {
        if(participantUser instanceof ParticipantAdmin){
           ((ParticipantAdmin) participantUser).banUser(name);
        }
    }

}
