package com.brice.enee408aprojectrobotcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WifiConnection extends AppCompatActivity {

    public void connectAndGoToMainMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), Main_Menu.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_connection);
    }
}
