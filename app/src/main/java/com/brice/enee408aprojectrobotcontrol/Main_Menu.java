package com.brice.enee408aprojectrobotcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main_Menu extends AppCompatActivity {

    private WifiClientExec client;
    private ScrollView controlButtons;
    private Button startClient;

    public void manualButtonOnClick(View view) {
        // Load Manual Operation View
        Intent intent = new Intent(getApplicationContext(), ManualControl.class);
        intent.putExtra("client", client);
        startActivity(intent);
    }

    public void autonomousButtonOnClick(View view) {
        // Load Autonomous Operation View
        Intent intent = new Intent(getApplicationContext(), AutonomousControl.class);
        //intent.putExtra("server", server);
        startActivity(intent);
    }

    public void facetrackingButtonOnClick(View view) {
        // Load facetracking Operation View
        Intent intent = new Intent(getApplicationContext(), FacetrackingControl.class);
        //intent.putExtra("server", server);
        startActivity(intent);
    }

    public void startEspClientOnClick(View view) {
        controlButtons.setVisibility(View.VISIBLE);
        InetAddress ipAddr = null;
        try {
            ipAddr = InetAddress.getByName("192.168.43.193");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        client = new WifiClientExec(80, ipAddr);
        //Toast.makeText(Main_Menu.this,"Server is running!",Toast.LENGTH_LONG).show();
        makeToast("Client is connected");
        startClient.setVisibility(View.INVISIBLE);

        //ListenForConnection listen = new ListenForConnection();
        //Thread t = new Thread(listen);
        //t.start();

    }

    public void makeToast(String message) {
        Toast.makeText(Main_Menu.this,message,Toast.LENGTH_LONG).show();
    }

    /*@Override
    public void onBackPressed() {
        Main_Menu.this.closeContextMenu();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);
        controlButtons = (ScrollView) findViewById(R.id.ControlButtons);
        startClient = (Button) findViewById(R.id.startClient);
    }

    /*
    private class ListenForConnection implements Runnable {

        @Override
        public void run() {
            while(!(server.isRaspiConnected() && server.isEspConnected())) {
                if(server.isEspConnected()) {
                    makeToast("ESP32 is connected");
                    //Toast.makeText(Main_Menu.this,"ESP32 is connected!",Toast.LENGTH_LONG).show();
                }
                if(server.isRaspiConnected()) {
                    makeToast("Raspi is connected");
                    //Toast.makeText(Main_Menu.this,"Raspi is connected!",Toast.LENGTH_LONG).show();
                }
            }
            controlButtons.setVisibility(View.VISIBLE);
        }
    }

     */

}
