package experis.ds.particpants;

import experis.ds.rooms.Lobby;
import experis.ds.rooms.Room;

import java.util.concurrent.TimeUnit;

public class ParticipantBot implements Participant, Runnable{
    private final long period;
    private final TimeUnit timeUnit;
    private final Room room = Lobby.getLobby();
    private final String[] words = new String[]{"Hello everyone", "Welcome to Aviram's project",
                                                "Please remember to be nice to other people"};

    public ParticipantBot(TimeUnit timeUnit, long period){
       this.period = period;
       this.timeUnit = timeUnit;
    }

    @Override
    public void run() {
        while(true){
            try {
                timeUnit.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendMessage(words[(int) (Math.random() * words.length)]);
        }
    }

    @Override
    public void sendMessage(String msg) {
        room.printInRoom(msg);
    }

}
