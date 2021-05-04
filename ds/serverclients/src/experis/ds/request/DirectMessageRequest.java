package experis.ds.request;

import experis.ds.particpants.ParticipantUser;
import java.util.concurrent.ConcurrentHashMap;

public class DirectMessageRequest implements Request {
    private final ConcurrentHashMap<String, ParticipantUser> participants;

    public DirectMessageRequest(ConcurrentHashMap<String, ParticipantUser> participants) {
      this.participants = participants;
    }

    @Override
    public void makeRequest(ParticipantUser participant, String msg) {
        String nameOfReceiver = getFirstWord(msg);
        String txt = getMessage(msg);
        ParticipantUser receiver;
        if(txt == null){
            return;
        }

        synchronized (participants) {
            receiver = participants.get(nameOfReceiver);
        }
        if(receiver.getRoom() == participant.getRoom()){
            participant.sendMessageToOne(receiver, txt);
        }
    }

    private String getFirstWord(String msg) {
        int pos = msg.indexOf(' ');
        if(pos == -1){
            pos = msg.length();
        }
        return msg.substring(0, pos);
    }

    private String getMessage(String msg){
        int pos = msg.indexOf(' ');
        if(pos == -1){
            return null;
        }
        return msg.substring(pos).trim();
    }

}
