package experis.ds.rooms;

public class Lobby extends Room{
    private final static Lobby lobby = new Lobby("Lobby");

    public Lobby(String name) {
        super(name);
    }

    public static Lobby getLobby() {
        return lobby;
    }
}
