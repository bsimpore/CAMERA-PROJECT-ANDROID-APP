package com.brice.enee408aprojectrobotcontrol;

import java.io.Serializable;

public class WifiServerExec implements Serializable {

    private WifiServer server;

    /**
     * Constructor that creates an instance of the server class
     * with the provided port number
     * @param port provided port number
     */
    public WifiServerExec(int port) {
        server = new WifiServer(port);
    }

    public boolean isEspConnected() {
        return server.isEspConnected();
    }

    public boolean isRaspiConnected() {
        return server.isRaspiConnected();
    }

    public void sendCommandToESP(String command) {
        server.sendCommandToESP(command);
    }

    public void sendCommandToPI(String command) {
        server.sendCommandToPI(command);
    }

    public StringBuffer receivedFromESP() {
        return server.receivedFromESP();
    }

    public StringBuffer receivedFromPI() {
        return server.receivedFromPI();
    }

    /**
     * Overloaded startServer method with no
     * argument
     */
    public void startServer() {

        Thread t = new Thread(server);
        t.start();
    }

}
