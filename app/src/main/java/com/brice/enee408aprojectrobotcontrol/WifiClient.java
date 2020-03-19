package com.brice.enee408aprojectrobotcontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketPermission;
import java.net.InetAddress;
import java.net.UnknownHostException;


class WifiClient implements Runnable {

    private BufferedReader in;
    private PrintWriter out;
    private int serverPort;
    private InetAddress IP;
    private StringBuffer send;
    private StringBuffer received;

    protected WifiClient(int port, InetAddress IP) {
        this.serverPort = port;
        this.IP = IP;
        send = new StringBuffer("");
        received = new StringBuffer("");
    }

    protected void send(String message) {
        send.replace(0,send.length(), message);
    }

    protected StringBuffer received() {
        return received;
    }


    @Override
    public void run() {
        try {

            Socket sock = new Socket(IP, serverPort);

            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            while(true) {

                String str = in.readLine();
                if(!str.equals("")) {
                    received.replace(0, received.length(), str);
                }
                else if(!send.toString().equals("")) {
                    out.println(send.toString());
                    send.replace(0, send.length(), "");
                }

            }
        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
