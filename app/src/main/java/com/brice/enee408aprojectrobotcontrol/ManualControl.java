package com.brice.enee408aprojectrobotcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.VideoView;

import com.longdo.mjpegviewer.MjpegView;

import static android.view.View.VISIBLE;

public class ManualControl extends AppCompatActivity {

    private Button upButton;
    private Button rightButton;
    private Button downButton;
    private Button leftButton;
    private WifiClientExec client;
    private MjpegView mjpegview;

    public void enableVideo(View view) {
        Switch videoSwitch = (Switch) findViewById(R.id.videoSwitch);
        if(videoSwitch.isChecked()) {
            mjpegview.setVisibility(View.VISIBLE);
            mjpegview.setMode(MjpegView.MODE_FIT_WIDTH);
            mjpegview.setAdjustHeight(true);
            mjpegview.setUrl("http://192.168.43.188:8000/stream.mjpg");
            mjpegview.startStream();
        }
        else {
            mjpegview.stopStream();
            mjpegview.setVisibility(View.INVISIBLE);
        }
    }

    public void upButtonOnClick(View view) {
        client.send("u");
    }

    public void rightButtonOnClick(View view) {
        client.send("r");
    }

    public void downButtonOnClick(View view) {
        client.send("d");
    }

    public void leftButtonOnClick(View view) {
        client.send("l");
    }

    public void stopButtonOnClick(View view) {
        client.send("s");
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_control);
        mjpegview = (MjpegView) findViewById(R.id.mjpegview);
        upButton = (Button) findViewById(R.id.upButton);
        rightButton = (Button) findViewById(R.id.rightButton);
        downButton = (Button) findViewById(R.id.downButton);
        leftButton = (Button) findViewById(R.id.leftButton);
        client = (WifiClientExec) getIntent().getSerializableExtra("client");
        try {
            client.startClient();
            client.send("m");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
