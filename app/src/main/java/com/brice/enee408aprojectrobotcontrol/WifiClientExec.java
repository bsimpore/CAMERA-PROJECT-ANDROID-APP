package com.brice.enee408aprojectrobotcontrol;

import java.io.Serializable;
import java.net.InetAddress;

public class WifiClientExec implements Serializable {

    private WifiClient client;
    private Thread t;

    /**
     * Constructor that creates an instance of
     * the client class with the server's port number
     * @param port server's port number
     */
    public WifiClientExec(int port, InetAddress IP) {

        client = new WifiClient(port, IP);

    }

    /**
     * method that creates the client's thread and
     * starts it
     */
    public void startClient() throws Exception {

        t = new Thread(client);
        t.start();

    }

    public void send(String message) {
        client.send(message);
    }

    public StringBuffer received() {
        return client.received();
    }

    public void stopClient() {
        t.interrupt();
    }

}
