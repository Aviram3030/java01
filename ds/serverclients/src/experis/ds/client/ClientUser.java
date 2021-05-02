package experis.ds.client;

import experis.ds.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientUser implements Client {
    private String name;
    private final BufferedReader input;
    private final PrintWriter output;
    private Room room;

    public ClientUser(Socket client, Room room) throws IOException {
        var inputStream = new InputStreamReader(client.getInputStream());
        input = new BufferedReader(inputStream);
        name = input.readLine();
        output = new PrintWriter(client.getOutputStream(), true);
        this.room = room;
        room.addClient(this);
    }

    public void sendMessage(String msg){
        room.printInRoom(name + " says: " + msg);
    }

    public void printMessage(String msg){
        output.println(msg);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRoom(Room room){
        this.room.removeClient(this);
        this.room = room;
        room.addClient(this);
    }
}
