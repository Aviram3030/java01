package experis.ds.request.other;

import experis.ds.particpants.ParticipantUser;

public interface Request {
    void makeRequest(ParticipantUser participantUser, String msg);
}
