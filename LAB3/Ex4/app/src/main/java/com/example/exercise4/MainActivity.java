package com.example.exercise4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import com.example.exercise4.GraphicView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GraphicView(this));

        GraphicView.mediaPlayer = MediaPlayer.create(GraphicView.ctext, R.raw.eminem_rap_god);
        GraphicView.mediaPlayer.start();
    }
}