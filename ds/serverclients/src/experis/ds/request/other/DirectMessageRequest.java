package experis.ds.request.other;

import experis.ds.particpants.ParticipantUser;

public class DirectMessageRequest implements Request {

    @Override
    public void makeRequest(ParticipantUser participantUser, String msg) {
        String nameOfReceiver = getFirstWord(msg);
        String txt = getMessage(msg);

        if(txt == null){
            return;
        }
        participantUser.sendMessageToOne(nameOfReceiver, txt);

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
