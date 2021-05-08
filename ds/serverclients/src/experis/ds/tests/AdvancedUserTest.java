package experis.ds.tests;

import org.junit.jupiter.api.Test;

class AdvancedUserTest {

    @Test
    void two_users() throws InterruptedException {
        Thread serverThread = new Thread(new ServerTask());
        serverThread.start();
        Thread clientThread = new Thread(new ClientTask());
        Thread secondClientThread = new Thread(new SecondClientTask());
        clientThread.start();
        Thread.sleep(1000);
        secondClientThread.start();

        clientThread.join();
        secondClientThread.join();
        serverThread.interrupt();
    }
}