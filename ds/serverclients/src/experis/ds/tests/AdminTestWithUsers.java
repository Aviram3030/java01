package experis.ds.tests;
import org.junit.jupiter.api.Test;

class AdminTestWithUsers {


    @Test
    void admin_two_users_test() throws InterruptedException {
        Thread serverThread = new Thread(new ServerTask());
        serverThread.start();
        Thread clientThread = new Thread(new ClientTask());
        Thread secondClientThread = new Thread(new SecondClientTask());
        Thread adminThread = new Thread(new AdminTask());


        clientThread.start();
        Thread.sleep(1000);
        secondClientThread.start();
        adminThread.start();

        try {
            clientThread.join();
            secondClientThread.join();
            adminThread.join();
            serverThread.interrupt();
        }catch(Exception ignored){
        }
    }
}