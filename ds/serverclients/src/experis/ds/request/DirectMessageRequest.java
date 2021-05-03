package experis.ds.request;

import experis.ds.particpants.ParticipantUser;
import java.util.concurrent.ConcurrentHashMap;

public class DirectMessageRequest implements Request {
    private final ConcurrentHashMap<String, ParticipantUser> participants;

    public DirectMessageRequest(ConcurrentHashMap<String, ParticipantUser> participants) {
      this.participants = participants;
    }

    @Override
    public void request(ParticipantUser participant, String msg) {
        String nameOfReceiver = getReceiver(msg);
        String txt = getMessage(msg);
        ParticipantUser receiver;
        if(txt == null){
            return;
        }

        synchronized (this.participants) {
            receiver = this.participants.get(nameOfReceiver);
        }
        if(receiver.getRoom() == participant.getRoom()){
            participant.sendMessageToOne(receiver, txt);
        }
    }

    private String getReceiver(String msg) {
        int pos = msg.indexOf(' ');
        return msg.substring(0, pos);
    }

    private String getMessage(String msg){
        int pos = msg.indexOf(' ');
        return msg.substring(pos);
    }

}
