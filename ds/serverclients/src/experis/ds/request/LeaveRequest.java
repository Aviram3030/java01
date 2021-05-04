package experis.ds.request;

import experis.ds.particpants.ParticipantUser;
import experis.ds.rooms.Lobby;

public class LeaveRequest implements Request{
    @Override
    public void makeRequest(ParticipantUser clientUser, String msg) {
        clientUser.setRoom(Lobby.getLobby());
    }
}
