package experis.ds.tests;

import experis.ds.server.Server;
import experis.ds.server.ServerHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class ServerTask implements Runnable {

    @Override
    public void run() {
        try {
            Server server = new Server(7777);
            server.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientTask implements Runnable {

    final int N = 1000;

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 7777);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            ServerHandler serverHandler = new ServerHandler(socket);
            Thread serverThread = new Thread(serverHandler);
            serverThread.start();

            output.println("Aviram"); // name
            output.println("2"); //user
            output.println("list");
            Thread.sleep(N);

            output.println("Hi");

            output.println("room java");
            Thread.sleep(N);

            output.println("list");
            Thread.sleep(N);

            output.println("leave");
            Thread.sleep(N);

            output.println("quit");
            Thread.sleep(N);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SecondClientTask implements Runnable {
    final int N = 1000;

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 7777);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            ServerHandler serverHandler = new ServerHandler(socket);
            Thread serverThread = new Thread(serverHandler);
            serverThread.start();

            output.println("Aviram"); // name
            output.println("2"); //user

            output.println("list");
            Thread.sleep(N);

            output.println("Hello guys");
            Thread.sleep(N);

            output.println("msg Aviram my name is Aviram too!");

            output.println("list this is a message!");
            Thread.sleep(N);

            output.println("room java");
            Thread.sleep(N);

            System.out.println("this is a message:");
            output.println("room");
            Thread.sleep(N);

            output.println("quit");
            Thread.sleep(N);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class AdminTask implements Runnable {
    final int N = 1000;

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 7777);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            ServerHandler serverHandler = new ServerHandler(socket);
            Thread serverThread = new Thread(serverHandler);
            serverThread.start();

            output.println("Admin"); // name
            Thread.sleep(N);
            output.println("1"); //admin
            Thread.sleep(N);
            output.println("1234"); //password

            output.println("list");
            Thread.sleep(N);


            output.println("room project");
            Thread.sleep(N);

            output.println("list");
            Thread.sleep(N);

            output.println("ban Aviram");
            Thread.sleep(N);

            output.println("Aviram");
            Thread.sleep(N);

            output.println("Now i'm Aviram");
            Thread.sleep(N);

            output.println("shutdown");
            Thread.sleep(N * 11); //shutdown timer

            System.out.println("Nothing happens");

            output.println("list");
            Thread.sleep(N);

            output.println("quit");
            Thread.sleep(N);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }
}

class ClassesForTest {
}