package experis.ds.request;

import experis.ds.particpants.ParticipantUser;

import java.io.IOException;

public class QuitRequest implements Request{
    @Override
    public void makeRequest(ParticipantUser participantUser, String msg) {
        try {
            participantUser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
