package experis.ds.request.word;

import experis.ds.particpants.ParticipantUser;
import experis.ds.rooms.Lobby;

public class LeaveRequest implements OneWordRequest {
    @Override
    public void makeRequest(ParticipantUser participantUser) {
        participantUser.setRoom(Lobby.getLobby());
    }
}
