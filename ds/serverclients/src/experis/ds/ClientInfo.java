package experis.ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientInfo {
    private final Socket client;
    private final BufferedReader input;
    private final PrintWriter output;
    private Room room;

    public ClientInfo(Socket client, Room room) throws IOException {
        this.client = client;
        var inputStream = new InputStreamReader(client.getInputStream());
        input = new BufferedReader(inputStream);
        output = new PrintWriter(client.getOutputStream(), true);
        this.room = room;
    }

    public void sendMessage(String msg){
        room.printInRoom(msg);
    }

    public void printMessage(String msg){
        output.println(msg);
    }

    public void setRoom(Room room){
        this.room = room;
    }
}
