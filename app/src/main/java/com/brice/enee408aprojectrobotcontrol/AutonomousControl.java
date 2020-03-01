package com.brice.enee408aprojectrobotcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.VideoView;

import com.longdo.mjpegviewer.MjpegView;

public class AutonomousControl extends AppCompatActivity {

    public void enableVideo(View view) {
        Switch videoSwitch = (Switch) findViewById(R.id.videoSwitch2);
        MjpegView mjpegview = (MjpegView) findViewById(R.id.mjpegview);
        if(videoSwitch.isChecked()) {
            mjpegview.setVisibility(View.VISIBLE);
        }
        else {
            mjpegview.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autonomous_control);
    }
}
