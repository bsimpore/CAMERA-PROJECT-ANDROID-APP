package com.brice.enee408aprojectrobotcontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;


class WifiClient {

    private BufferedReader in;
    private PrintWriter out;
    private int serverPort;
    private InetAddress IP;
    Socket sock;

    protected WifiClient(int port, byte[] IP) {
        this.serverPort = port;
        try {
            this.IP = InetAddress.getByAddress(IP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.sock = null;
    }

    protected boolean connect() throws IOException {

        sock = new Socket(IP, serverPort);
        if(sock.isConnected()) {
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        }
        return sock.isConnected();
    }

    protected void send(String message) {
        out.println(message);
    }

    protected String receive() {
        try {
            return  in.readLine();
        } catch (IOException e) {
            return "";
        }

    }

}
