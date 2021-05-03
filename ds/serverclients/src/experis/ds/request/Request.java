package experis.ds.request;

import experis.ds.particpants.ParticipantUser;

public interface Request {
    void request(ParticipantUser clientUser, String msg);
}
