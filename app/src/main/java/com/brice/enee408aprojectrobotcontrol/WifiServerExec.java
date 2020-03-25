package com.brice.enee408aprojectrobotcontrol;

public class WifiServerExec {

    private WifiServer server;

    /**
     * Constructor that creates an instance of the server class
     * with the provided port number
     * @param port provided port number
     */
    public WifiServerExec(int port) {
        server = new WifiServer(port);
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
