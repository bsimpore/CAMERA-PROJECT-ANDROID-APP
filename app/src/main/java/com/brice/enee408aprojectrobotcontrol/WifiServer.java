package com.brice.enee408aprojectrobotcontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.ArrayList;

public class WifiServer implements Runnable {

    private final int PORTNUMBER;
    //private final InetAddress IP;
    private String ipString;
    private ServerThreadForESP esp;
    private ServerThreadForPI raspi;

    /**
     * Server class constructor taking
     * the desired port number as an input
     * @param port desired port number
     */
    public WifiServer(int port) {

        PORTNUMBER = port;
        ipString = "192.168.1.226";

    }

    public void sendCommandToESP(String command) {
        esp.send.add(new StringBuffer(command));
    }

    public void sendCommandToPI(String command) {
        raspi.send.add(new StringBuffer(command));
    }

    public String receivedFromESP() {
        if(!esp.received.isEmpty()) {
            return esp.received.remove(0).toString();
        }
        else {
            return "";
        }
    }

    public String receivedFromPI() {
        if(!raspi.received.isEmpty()) {
            return raspi.received.remove(0).toString();
        }
        else {
            return "";
        }
    }

    /**
     * Run method. In this method, a server socket is
     * created. It has an infinite loop that listen to a client
     * socket to connect to the server socket and then send commands to
     * the client's to validate its name. Once the client name is accepted,
     * the client can start sending messages.
     */
    @Override
    public void run() {

        try {
            ServerSocket listen = new ServerSocket(PORTNUMBER);
            System.out.println("Server is Running");

            while(true) {

                Socket client = listen.accept();

                PrintWriter out = new PrintWriter(client.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                if(client.getInetAddress().equals(InetAddress.getByName("192.168.4.1"))) {
                    esp = new ServerThreadForESP(in, out);
                    Thread t = new Thread(esp);
                    t.run();
                }
                else {
                    raspi = new ServerThreadForPI(in, out);
                    Thread t = new Thread(raspi);
                    t.run();
                }

            }
        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    private class ServerThreadForESP implements Runnable {
        private BufferedReader in;
        private PrintWriter out;
        private ArrayList<StringBuffer> send;
        private ArrayList<StringBuffer> received;

        public ServerThreadForESP(BufferedReader in, PrintWriter out) {
            this.in = in;
            this.out = out;
            send = new ArrayList<StringBuffer>();
            received = new ArrayList<StringBuffer>();
        }

        @Override
        public void run() {

            try {

                if(in.ready()) {
                    received.add(new StringBuffer(in.readLine()));
                }

                if(!send.isEmpty()) {
                    out.println(send.remove(0).toString());
                }

            }
            catch(IOException e) {
                e.printStackTrace();
            }

        }
    }

    private class ServerThreadForPI implements Runnable {
        private BufferedReader in;
        private PrintWriter out;
        private ArrayList<StringBuffer> send;
        private ArrayList<StringBuffer> received;

        public ServerThreadForPI(BufferedReader in, PrintWriter out) {
            this.in = in;
            this.out = out;
            send = new ArrayList<StringBuffer>();
            received = new ArrayList<StringBuffer>();
        }

        @Override
        public void run() {

            try {

                if(in.ready()) {
                    received.add(new StringBuffer(in.readLine()));
                }

                if(!send.isEmpty()) {
                    out.println(send.remove(0).toString());
                }

            }
            catch(IOException e) {
                e.printStackTrace();
            }

        }
    }

}
