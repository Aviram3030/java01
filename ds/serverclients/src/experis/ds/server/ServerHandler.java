package experis.ds.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandler implements Runnable{
    private final Socket server;
    private final BufferedReader input;

    public ServerHandler(Socket server) throws IOException {
        this.server = server;
        input = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        while(true){
            try {
                String msg = input.readLine();
                if(msg == null){
                    break;
                }
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            server.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
