package com.example.ex2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupColors = findViewById(R.id.radioGroupColors);
        radioGroupColors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int colorId = 0;

                if (checkedId == R.id.radioButtonRed) {
                    colorId = Color.RED;
                } else if (checkedId == R.id.radioButtonGreen) {
                    colorId = Color.GREEN;
                } else if (checkedId == R.id.radioButtonBlue) {
                    colorId = Color.BLUE;
                } else if (checkedId == R.id.radioButtonGray) {
                    colorId = Color.GRAY;
                }

                getWindow().getDecorView().setBackgroundColor(colorId);
            }
        });
    }
}