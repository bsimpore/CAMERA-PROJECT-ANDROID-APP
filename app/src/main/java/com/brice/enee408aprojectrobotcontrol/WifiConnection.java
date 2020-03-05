package com.brice.enee408aprojectrobotcontrol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class WifiConnection extends AppCompatActivity {

    WifiClient client;
    EditText ssid;
    EditText password;

    public void connectAndGoToMainMenu(View view) {

        byte[] ipAddr = new byte[] {(byte)192, (byte)168, 4, 1 };
        client = new WifiClient(80, ipAddr);
        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
            EmptyFieldDialog dialog = new EmptyFieldDialog();
            dialog.show(getSupportFragmentManager(), "Empty Fields");
            e.printStackTrace();
        }

        ssid = (EditText) findViewById(R.id.SSIDField);
        password = (EditText) findViewById(R.id.passwordField);

        if(ssid.getText().toString().equals("") || password.getText().toString().equals("")) {
            EmptyFieldDialog dialog = new EmptyFieldDialog();
            dialog.show(getSupportFragmentManager(), "Empty Fields");
        }
        else {

            ProgressDialog mdialog = new ProgressDialog(WifiConnection.this);
            mdialog.setMessage("Please wait...");
            mdialog.setCanceledOnTouchOutside(false);
            mdialog.show();

            String send_ssid = ssid.getText().toString();
            String send_password = password.getText().toString();

            client.send(send_ssid);
            while(!client.receive().equals("OK")) {
            }

            client.send(send_password);
            while(!client.receive().equals("OK")) {
            }

            String confirm = client.receive();
            while(confirm.equals("")) {
                //confirm = client.receive();
            }

            if(confirm.equals("connected")) {

                SuccessfulConnectionDialog dialog = new SuccessfulConnectionDialog();
                dialog.show(getSupportFragmentManager(), "Connected");

                Intent intent = new Intent(getApplicationContext(), Main_Menu.class);
                startActivity(intent);
            }
            else {
                FailedConnectionDialog fdialog = new FailedConnectionDialog();
                fdialog.show(getSupportFragmentManager(), "Failed");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_connection);
    }
}
