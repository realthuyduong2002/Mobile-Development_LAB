package com.example.exercise2;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnReadData = findViewById(R.id.btnReadData);
        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btnReadData.setVisibility(View.GONE);

                    InputStream inputStream = getResources().openRawResource(R.raw.myfile);
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    inputStream.close();

                    EditText tempEditText = new EditText(MainActivity.this);
                    tempEditText.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    tempEditText.setText(content.toString());

                    ConstraintLayout layout = findViewById(R.id.main);
                    layout.addView(tempEditText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button btnWriteData = findViewById(R.id.btnWriteData);
        btnWriteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData();
            }
        });

        ConstraintLayout layout = findViewById(R.id.main);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(btnWriteData.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.applyTo(layout);
    }

    public void writeData() {
        EditText editdata = findViewById(R.id.editText);
        try {
            FileOutputStream out = openFileOutput("myfile.txt", Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(editdata.getText().toString());
            writer.close();
            // Sucessful saved data
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
