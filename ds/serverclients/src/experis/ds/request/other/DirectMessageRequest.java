package experis.ds.request.other;

import experis.ds.particpants.ParticipantUser;

import java.util.concurrent.ConcurrentHashMap;

public class DirectMessageRequest implements Request {
    private final ConcurrentHashMap<String, ParticipantUser> participants;

    public DirectMessageRequest(ConcurrentHashMap<String, ParticipantUser> participants) {
      this.participants = participants;
    }

    @Override
    public void makeRequest(ParticipantUser participantUser, String msg) {
        System.out.println(msg);
        String nameOfReceiver = getFirstWord(msg);
        String txt = getMessage(msg);
        ParticipantUser receiver;
        if(txt == null){
            return;
        }

        synchronized (participants) {
            System.out.println(nameOfReceiver);
            receiver = participants.get(nameOfReceiver);
        }
        if(receiver == null){
            return;
        }

        if(receiver.getRoom() == participantUser.getRoom()){
            participantUser.sendMessageToOne(receiver, txt);
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
