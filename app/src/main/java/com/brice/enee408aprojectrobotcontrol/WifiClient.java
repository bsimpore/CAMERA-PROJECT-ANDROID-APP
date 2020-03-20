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
    private int flag;


    protected WifiClient(int port, InetAddress IP) {
        this.serverPort = port;
        this.IP = IP;
        send = new StringBuffer("");
        received = new StringBuffer("");
        flag = 0;
    }

    protected void send(String message) {
        send.replace(0,send.length(), message);
    }

    protected StringBuffer received() {
        StringBuffer ret = new StringBuffer(received.toString());
        received.replace(0, received.length(), "");
        return ret;
    }


    @Override
    public void run() {
        try {

            Socket sock = new Socket(IP, serverPort);

            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            while(true) {

                if(flag == 1) {
                    if (in.ready()) {
                        String str = in.readLine();
                        received.replace(0, received.length(), str);
                        flag = 0;
                    }
                }
                else {
                    if(!send.toString().equals("")) {
                        out.println(send.toString());
                        send.replace(0, send.length(), "");
                        flag = 1;
                    }
                }

            }
        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
