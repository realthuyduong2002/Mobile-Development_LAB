package com.example.bt3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        final Button timeButton = (Button) findViewById(R.id.btn_watch_time);
        final AlertDialog ad = new AlertDialog.Builder(this).create();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date t = new Date();
                String message = "Thoi gian hien hanh: " + t.toLocaleString();
                ad.setMessage(message);
                ad.show();
            }
        });
    }
}