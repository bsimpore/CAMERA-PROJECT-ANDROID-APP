package com.brice.enee408aprojectrobotcontrol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class WifiConnection extends AppCompatActivity {

    private WifiClientExec client;
    private EditText ssid;
    private EditText password;

    public void connectAndGoToMainMenu(View view) {

        InetAddress ipAddr = null;
        try {
            ipAddr = InetAddress.getByName("192.168.4.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        client = new WifiClientExec(80, ipAddr);

        ssid = (EditText) findViewById(R.id.SSIDField);
        password = (EditText) findViewById(R.id.passwordField);

        if(ssid.getText().toString().equals("") || password.getText().toString().equals("")) {
            EmptyFieldDialog dialog = new EmptyFieldDialog();
            dialog.show(getSupportFragmentManager(), "Empty Fields");
        }
        else {
            try {
                client.startClient();
            } catch (Exception e) {
                e.printStackTrace();
                EmptyFieldDialog dialog = new EmptyFieldDialog();
                dialog.show(getSupportFragmentManager(), "Empty Fields");
                e.printStackTrace();
            }

            ProgressDialog mdialog = new ProgressDialog(WifiConnection.this);
            mdialog.setMessage("Please wait...");
            mdialog.setCanceledOnTouchOutside(false);
            mdialog.show();

            String send_ssid = ssid.getText().toString();
            String send_password = password.getText().toString();

            client.send(send_ssid);
            while(!client.received().toString().equals("OK")) {
            }

            client.send(send_password);
            while(!client.received().toString().equals("OK")) {
            }

            client.send("end");    // end
            String confirm = client.received().toString();
            while(confirm.equals("")) {
                confirm = client.received().toString();
            }

            if(confirm.equals("connected")) {

                SuccessfulConnectionDialog dialog = new SuccessfulConnectionDialog();
                dialog.show(getSupportFragmentManager(), "Connected");

                client.stopClient();

                Intent intent = new Intent(getApplicationContext(), Main_Menu.class);
                startActivity(intent);
            }
            else {
                FailedConnectionDialog fdialog = new FailedConnectionDialog();
                fdialog.show(getSupportFragmentManager(), "Failed");
                client.stopClient();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_connection);
    }
}
