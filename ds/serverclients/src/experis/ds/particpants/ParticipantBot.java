package experis.ds.particpants;

import experis.ds.rooms.Lobby;
import experis.ds.rooms.Room;

import java.util.concurrent.TimeUnit;

public class ParticipantBot implements Participant, Runnable{
    private long period;
    private TimeUnit timeUnit;
    private Room room = Lobby.getLobby();

    public ParticipantBot(TimeUnit timeUnit, long period){
       this.period = period;
       this.timeUnit = timeUnit;
    }
    
    @Override
    public void sendMessage(String msg) {
        room.printInRoom(msg);
    }

    @Override
    public void run() {
        while(true){
            try {
                timeUnit.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendMessage("HELLO EVERYONE");
        }
    }
}
