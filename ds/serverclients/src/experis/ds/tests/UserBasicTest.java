package experis.ds.tests;

import experis.ds.server.Server;
import experis.ds.server.ServerHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class UserBasicTest {

    @Test
    void first_test() throws InterruptedException {
        Thread serverThread = new Thread(new ServerTask());
        serverThread.start();
        Thread clientThread = new Thread(new ClientTask());
        clientThread.start();

        clientThread.join();
        serverThread.interrupt();
    }

}

