package com.brice.enee408aprojectrobotcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Main_Menu extends AppCompatActivity {


    public void manualButtonOnClick(View view) {
        // Load Manual Operation View
        Intent intent = new Intent(getApplicationContext(), ManualControl.class);
        startActivity(intent);
    }

    public void autonomousButtonOnClick(View view) {
        // Load Autonomous Operation View
        Intent intent = new Intent(getApplicationContext(), AutonomousControl.class);
        startActivity(intent);
    }

    public void facetrackingButtonOnClick(View view) {
        // Load facetracking Operation View
        Intent intent = new Intent(getApplicationContext(), FacetrackingControl.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);
    }

}
