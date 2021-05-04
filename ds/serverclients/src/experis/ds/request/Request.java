package experis.ds.request;

import experis.ds.particpants.ParticipantUser;

public interface Request {
    void makeRequest(ParticipantUser clientUser, String msg);
}
